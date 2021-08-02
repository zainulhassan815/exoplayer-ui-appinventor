package com.dreamers.videoplayer

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.Util
import java.io.InputStream
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {

    private var playerView: PlayerView? = null
    private var videoUrl =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    private val music = "https://storage.googleapis.com/exoplayer-test-media-0/Jazz_In_Paris.mp3"
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

        val mediaItem1 = MediaItem.fromUri(videoUrl)
        val mediaItem2 = MediaItem.fromUri(music)

        player = SimpleExoPlayer
            .Builder(this)
            .build()
            .also { exoplayer ->
                exoplayer.addMediaItem(mediaItem2)
                exoplayer.addMediaItem(mediaItem1)

                exoplayer.seekTo(currentWindow, playbackPosition)
                exoplayer.playWhenReady = shouldPlayWhenReady
                exoplayer.prepare()
            }

        playerView?.player = player
        playerView?.useArtwork = false
        playerView?.defaultArtwork = getAssetDrawable(applicationContext,"thumbnail.jpg")
    }

    private fun getAssetDrawable(context: Context, asset: String): Drawable? {
        return try {
            val inputStream : InputStream = context.assets.open(asset)
            val drawable: Drawable = Drawable.createFromStream(inputStream,null)
            inputStream.close()
            drawable
        } catch (e: Exception) {
            Log.e("VideoPlayer","getAssetDrawable | Error : $e")
            null
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