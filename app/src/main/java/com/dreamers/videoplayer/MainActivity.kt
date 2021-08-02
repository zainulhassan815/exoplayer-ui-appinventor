package com.dreamers.videoplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.Util

class MainActivity : AppCompatActivity() {

    private var playerView: PlayerView? = null
    private var videoUrl =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    private var player: SimpleExoPlayer? = null

    private var currentWindow: Int = 0
    private var playbackPosition: Long = 0L
    private var shouldPlayWhenReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val container: FrameLayout = findViewById(R.id.video_container)
        playerView = PlayerView(this)
        container.addView(
            playerView,
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        )

        initializePlayer()
    }

    private fun initializePlayer() {

        val mediaItem = MediaItem.Builder().setUri(videoUrl).build()

        player = SimpleExoPlayer
            .Builder(this)
            .build()
            .also { exoplayer ->
                playerView?.player = exoplayer
                exoplayer.setMediaItem(mediaItem)
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

}