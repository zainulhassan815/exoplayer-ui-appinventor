package com.dreamers.videoplayer

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.metadata.Metadata
import com.google.android.exoplayer2.text.Cue
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.*
import com.google.android.exoplayer2.ui.CaptionStyleCompat.EDGE_TYPE_DROP_SHADOW
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.exoplayer2.util.RepeatModeUtil.REPEAT_TOGGLE_MODE_NONE
import com.google.android.exoplayer2.util.Util

class MainActivity : AppCompatActivity(), StyledPlayerControlView.OnFullScreenModeChangedListener {

    private var playerView: StyledPlayerView? = null
    private var videoUrl =
        "https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8"
    private val music = "https://storage.googleapis.com/exoplayer-test-media-0/Jazz_In_Paris.mp3"
    private val subtitles =
        "https://raw.githubusercontent.com/benwfreed/test-subtitles/master/mmvo72166981784.vtt"
    private var player: SimpleExoPlayer? = null

    private var currentWindow: Int = 0
    private var playbackPosition: Long = 0L
    private var shouldPlayWhenReady = false
    private var trackSelector: DefaultTrackSelector? = null
    private var trackSelectorParameters: DefaultTrackSelector.Parameters? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trackSelector = DefaultTrackSelector(this)

        val container: FrameLayout = findViewById(R.id.video_container)
        playerView = StyledPlayerView(this)

        container.addView(
            playerView,
            0
        )
        initializePlayer()
    }

    private fun initializePlayer() {

        trackSelector = DefaultTrackSelector(this)
        trackSelectorParameters = DefaultTrackSelector.ParametersBuilder(this).build()

        playerView?.requestLayout()

        val items = mutableListOf(
            MediaItem.Builder().setUri(videoUrl).setSubtitles(
                mutableListOf(
                    MediaItem.Subtitle(
                        Uri.parse(subtitles),
                        MimeTypes.TEXT_VTT,
                        "en",
                        C.SELECTION_FLAG_DEFAULT
                    )
                ),
            ).build(),
            MediaItem.fromUri(music),
        )

        player = SimpleExoPlayer
            .Builder(this)
            .setTrackSelector(trackSelector!!)
            .build()
            .also { exoplayer ->

                playerView?.player = exoplayer
                playerView?.setControllerOnFullScreenModeChangedListener(this)

                exoplayer.addMediaItems(items)
                exoplayer.seekTo(currentWindow, playbackPosition)
                exoplayer.playWhenReady = shouldPlayWhenReady
                exoplayer.prepare()
                exoplayer.addListener(object : Player.Listener {

                    override fun onMetadata(metadata: Metadata) {
                        super.onMetadata(metadata)
                        Log.v(LOG_TAG, "Metadata : $metadata")
                    }

                    override fun onCues(cues: MutableList<Cue>) {
                        super.onCues(cues)
                        Log.v(LOG_TAG, "Cue : ${cues.map { cue -> cue.text }}")
                    }

                    override fun onRenderedFirstFrame() {
                        super.onRenderedFirstFrame()
                        Log.v(LOG_TAG, "First Frame Rendered")
                    }

                    override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                        super.onMediaItemTransition(mediaItem, reason)
                        Log.v(
                            LOG_TAG,
                            "onMediaItemTransition : MediaItem = $mediaItem | Reason : $reason"
                        )
                    }
                })
            }
    }

    private fun releasePlayer() {
        player?.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentWindowIndex
            shouldPlayWhenReady = this.playWhenReady
            release()
        }
        player = null
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer()
        }
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initializePlayer()
        }

    }

    companion object {
        private const val LOG_TAG = "ExoplayerTest"
    }

    override fun onFullScreenModeChanged(isFullScreen: Boolean) {
        // Testing fullscreen listener
        Log.v(LOG_TAG, "Fullscreen Changed : $isFullScreen")
        if (isFullScreen) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            hideSystemUI()
        } else {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            showSystemUI()
        }
    }

    private fun hideSystemUI() {
        supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = window.decorView.windowInsetsController ?: return
            controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            controller.hide(WindowInsets.Type.systemBars())
        } else {
            window.decorView.fitsSystemWindows = true
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    // Hide the nav bar and status bar
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        supportActionBar?.show()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = window.decorView.windowInsetsController ?: return
            controller.show(WindowInsets.Type.systemBars())
        } else {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_VISIBLE)
        }
    }


}