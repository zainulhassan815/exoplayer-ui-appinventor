package com.dreamers.videoplayer

import android.content.pm.ActivityInfo
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.metadata.Metadata
import com.google.android.exoplayer2.text.Cue
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerStyle
import com.google.android.exoplayer2.ui.StyledPlayerControlView
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.ui.TrackSelectionDialogBuilder
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.exoplayer2.util.RepeatModeUtil
import com.google.android.exoplayer2.util.Util

class MainActivity : AppCompatActivity(), StyledPlayerControlView.OnFullScreenModeChangedListener,
    StyledPlayerControlView.OnSettingsWindowDismissListener {

    private var playerView: StyledPlayerView? = null
    private var videoUrl =
        "https://multiplatform-f.akamaihd.net/i/multi/will/bunny/big_buck_bunny_,640x360_400,640x360_700,640x360_1000,950x540_1500,.f4v.csmil/master.m3u8"
    private val music = "https://storage.googleapis.com/exoplayer-test-media-0/Jazz_In_Paris.mp3"
    private val subtitles =
        "https://raw.githubusercontent.com/benwfreed/test-subtitles/master/mmvo72166981784.vtt"
    private var player: SimpleExoPlayer? = null

    private var currentWindow: Int = 0
    private var playbackPosition: Long = 0L
    private var shouldPlayWhenReady = false
    private var trackSelector: DefaultTrackSelector? = null
    private var listener: Player.Listener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v(LOG_TAG, "OnCreate")
        trackSelector = DefaultTrackSelector(this)

        playerView = StyledPlayerView(
            this,
            PlayerStyle(
                showShuffleButton = true,
                repeatToggleModes = RepeatModeUtil.REPEAT_TOGGLE_MODE_ONE + RepeatModeUtil.REPEAT_TOGGLE_MODE_ALL,
            )
        ).apply {
            setVideoSettingsButtonListener { showTrackSelectionDialog() }
        }
        findViewById<FrameLayout>(R.id.video_container).addView(playerView)
        initializePlayer()
    }

    private fun drawable(id: Int): Drawable? {
        return ResourcesCompat.getDrawable(resources, id, theme)
    }

    private fun showTrackSelectionDialog() {
        if (player?.trackSelector != null && TrackSelectionDialog.willHaveContent(player?.trackSelector as? DefaultTrackSelector)) {
            val renderIndex = getRenderIndex()
            val dialog = TrackSelectionDialogBuilder(
                this,
                "Select Video Quality",
                player?.trackSelector as DefaultTrackSelector,
                renderIndex ?: 0
            ).setAllowAdaptiveSelections(false).setShowDisableOption(true)
                .setTrackNameProvider { format -> "${format.width} x ${format.height}" }.build()

            dialog.show()
        }
    }

    private fun getRenderIndex(type: Int = C.TRACK_TYPE_VIDEO): Int? {
        val trackInfo = trackSelector?.currentMappedTrackInfo
        val renderCount = trackInfo?.rendererCount ?: 0
        for (renderIndex in 0 until renderCount) {
            val trackType: Int? = trackInfo?.getRendererType(renderIndex)
            if (trackType == type) return renderIndex
        }
        return null
    }

    private fun initializePlayer() {

        Log.v(LOG_TAG, "Initialize Player")

        trackSelector = DefaultTrackSelector(this)

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

                playerView?.apply {
                    player = exoplayer
                    setKeepContentOnPlayerReset(true)
                    setControllerOnFullScreenModeChangedListener(this@MainActivity)
                    setControllerOnSettingsWindowDismissListener(this@MainActivity)
                }

                exoplayer.addMediaItems(items)
                exoplayer.seekTo(currentWindow, playbackPosition)
                exoplayer.playWhenReady = shouldPlayWhenReady
                exoplayer.prepare()
                listener = object : Player.Listener {

                    override fun onPlaybackStateChanged(state: Int) {
                        super.onPlaybackStateChanged(state)
                        try {
                            if (state == Player.STATE_READY) {

                                val trackInfo = trackSelector?.currentMappedTrackInfo

                                // Get all renderers : video, audio etc
                                val renderCount = trackInfo?.rendererCount ?: 0

                                // render index
                                for (renderIndex in 0 until renderCount) {
                                    val trackType: Int? = trackInfo?.getRendererType(renderIndex)
                                    val isTrackSupported = isSupportedTrackType(trackType!!)

                                    if (isTrackSupported) {

                                        val trackName = getTrackTypeString(trackType)
                                        Log.v(LOG_TAG, "Track Type : $trackName")

                                        val trackGroupArray = trackInfo.getTrackGroups(renderIndex)
                                        Log.v(
                                            LOG_TAG,
                                            "Available Tracks Groups for $trackName : ${trackGroupArray.length}"
                                        )

                                        for (groupIndex in 0 until trackGroupArray.length) {
                                            Log.v(
                                                LOG_TAG,
                                                "Track Group Length At $groupIndex for $trackName : ${
                                                    trackGroupArray.get(groupIndex).length
                                                }"
                                            )
                                            val trackGroup = trackGroupArray.get(groupIndex)
                                            for (trackIndex in 0 until trackGroup.length) {
                                                Log.v(
                                                    LOG_TAG,
                                                    "Track In Group $groupIndex : ${
                                                        trackGroup.getFormat(trackIndex)
                                                    }"
                                                )
                                            }
                                        }

                                    }

                                }
                            }
                        } catch (e: Exception) {
                            Log.e(LOG_TAG, "Error : ${e.message}")
                        }
                    }

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
                }

                listener?.let { player?.addListener(it) }
            }
    }

    private fun isSupportedTrackType(trackType: Int) =
        when (trackType) {
            C.TRACK_TYPE_VIDEO,
            C.TRACK_TYPE_AUDIO,
            C.TRACK_TYPE_TEXT -> true
            else -> false
        }

    private fun getTrackTypeString(trackType: Int): String? =
        when (trackType) {
            C.TRACK_TYPE_VIDEO -> "Video"
            C.TRACK_TYPE_AUDIO -> "Audio"
            C.TRACK_TYPE_TEXT -> "Text"
            else -> null
        }


    private fun releasePlayer() {
        player?.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentWindowIndex
            shouldPlayWhenReady = this.playWhenReady
            listener?.let { removeListener(it) }
            release()
        }
        listener = null
        player = null
        Log.v(LOG_TAG, "releasePlayer : Released = ${player == null}")
    }

    override fun onStop() {
        Log.v(LOG_TAG, "OnStop")
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    override fun onPause() {
        Log.v(LOG_TAG, "OnPause")
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }

    override fun onResume() {
        Log.v(LOG_TAG, "OnResume")
        super.onResume()
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer()
        }
    }

    override fun onStart() {
        Log.v(LOG_TAG, "OnStart")
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

    override fun onDismiss(isFullScreen: Boolean) {
        Log.v(LOG_TAG, "Settings Window Dismissed")
        if (isFullScreen) {
            hideSystemUI()
        }
    }

    private fun hideSystemUI() {
        supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = window.decorView.windowInsetsController ?: return
            controller.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            controller.hide(WindowInsets.Type.systemBars())
        } else {
            window.decorView.fitsSystemWindows = true
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            // Hide the nav bar and status bar
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
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