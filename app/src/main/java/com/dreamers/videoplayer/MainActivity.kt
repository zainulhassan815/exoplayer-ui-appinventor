package com.dreamers.videoplayer

import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.CaptionStyleCompat
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.exoplayer2.util.Util

class MainActivity : AppCompatActivity() {

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
    var trackSelectorButton: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val container: FrameLayout = findViewById(R.id.video_container)
        playerView = StyledPlayerView(this).apply {
            subtitleView?.setStyle(
                CaptionStyleCompat(
                    Color.WHITE,
                    Color.BLUE,
                    Color.MAGENTA,
                    CaptionStyleCompat.EDGE_TYPE_DROP_SHADOW,
                    Color.CYAN,
                    Typeface.DEFAULT
                )
            )
            subtitleView?.setFractionalTextSize(.06f)
        }
        container.addView(
            playerView,
            0,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            ),
        )
        initializePlayer()
    }

    private fun initializePlayer() {

        trackSelector = DefaultTrackSelector(this)
        trackSelectorParameters = DefaultTrackSelector.ParametersBuilder(this).build()

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
                playerView?.setControllerVisibilityListener { trackSelectorButton?.visibility = it }

                exoplayer.addMediaItems(items)
                exoplayer.seekTo(currentWindow, playbackPosition)
                exoplayer.playWhenReady = shouldPlayWhenReady
                exoplayer.prepare()
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

}