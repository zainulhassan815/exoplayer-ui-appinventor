package com.google.android.exoplayer2.ui

data class PlayerViewAttributes(
    val useArtwork: Boolean = true,
    val surfaceType: Int = PlayerView.SURFACE_TYPE_TEXTURE_VIEW,
    val resizeMode: Int = AspectRatioFrameLayout.RESIZE_MODE_FIT,
    val controllerTimeout: Int = PlayerControlView.DEFAULT_SHOW_TIMEOUT_MS,
    val hideOnTouch: Boolean = true,
    val autoShowController: Boolean = true,
    val showBuffering: Int = PlayerView.SHOW_BUFFERING_WHEN_PLAYING,
    val useController: Boolean = true,
    val hideDuringAds: Boolean = true,
    val isDebugMode: Boolean = false,
)