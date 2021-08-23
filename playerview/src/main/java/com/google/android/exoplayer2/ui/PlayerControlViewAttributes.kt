package com.google.android.exoplayer2.ui

import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.DefaultControlDispatcher
import com.google.android.exoplayer2.ui.StyledPlayerControlView.DEFAULT_SHOW_TIMEOUT_MS
import com.google.android.exoplayer2.ui.StyledPlayerControlView.DEFAULT_TIME_BAR_MIN_UPDATE_INTERVAL_MS
import com.google.android.exoplayer2.util.RepeatModeUtil

data class PlayerControlViewAttributes(
    val rewindMs: Int = DefaultControlDispatcher.DEFAULT_REWIND_MS,
    val fastForwardMs: Int = DefaultControlDispatcher.DEFAULT_FAST_FORWARD_MS,
    val showTimeoutMs: Int = DEFAULT_SHOW_TIMEOUT_MS,
    val repeatToggleModes: Int = RepeatModeUtil.REPEAT_TOGGLE_MODE_ONE,
    val timeBarMinUpdateIntervalMs: Int = DEFAULT_TIME_BAR_MIN_UPDATE_INTERVAL_MS,
    val showRewindButton: Boolean = true,
    val showFastForwardButton: Boolean = true,
    val showPreviousButton: Boolean = true,
    val showNextButton: Boolean = true,
    val showShuffleButton: Boolean = true,
    val showSubtitleButton: Boolean = true,
    val animationEnabled: Boolean = true,
    val showVrButton: Boolean = false,
    val isDebugMode: Boolean = false,
    val hideAtMs: Long = C.TIME_UNSET,
) {
    constructor(mode: Boolean) : this(isDebugMode = mode)
}