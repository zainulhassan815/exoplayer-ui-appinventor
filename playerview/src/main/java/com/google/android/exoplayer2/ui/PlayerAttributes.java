package com.google.android.exoplayer2.ui;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultControlDispatcher;
import com.google.android.exoplayer2.util.RepeatModeUtil;

public class PlayerAttributes {

    private final int surfaceType;
    private final int timeBarMinUpdateIntervalMs = StyledPlayerControlView.DEFAULT_TIME_BAR_MIN_UPDATE_INTERVAL_MS;
    private final int showTimeoutMs = StyledPlayerControlView.DEFAULT_SHOW_TIMEOUT_MS;
    private final long hideAtMs = C.TIME_UNSET;
    private final boolean useArtWork;
    private final int resizeMode;
    private final int controllerTimeout;
    private final boolean hideOnTouch;
    private final boolean autoShowController;
    private final int showBuffering;
    private final boolean useController;
    private final boolean hideDuringAds;
    private final boolean isDebugMode;
    private final int rewindMs;
    private final int fastForwardMs;
    private final int repeatToggleModes;
    private final boolean showRewindButton;
    private final boolean showFastForwardButton;
    private final boolean showPreviousButton;
    private final boolean showNextButton;
    private final boolean showShuffleButton;
    private final boolean showSubtitleButton;
    private final boolean showFullscreenButton;
    private final boolean showVideoSettingsButton;
    private final boolean animationEnabled;

    public PlayerAttributes(int surfaceType,boolean useArtWork, int resizeMode, int controllerTimeout, boolean hideOnTouch, boolean autoShowController, int showBuffering, boolean useController, boolean hideDuringAds, boolean isDebugMode, int rewindMs, int fastForwardMs, int repeatToggleModes, boolean showRewindButton, boolean showFastForwardButton, boolean showPreviousButton, boolean showNextButton, boolean showShuffleButton, boolean showSubtitleButton, boolean showFullscreenButton,boolean showVideoSettingsButton, boolean animationEnabled) {
        this.surfaceType = surfaceType;
        this.useArtWork = useArtWork;
        this.resizeMode = resizeMode;
        this.controllerTimeout = controllerTimeout;
        this.hideOnTouch = hideOnTouch;
        this.autoShowController = autoShowController;
        this.showBuffering = showBuffering;
        this.useController = useController;
        this.hideDuringAds = hideDuringAds;
        this.isDebugMode = isDebugMode;
        this.rewindMs = rewindMs;
        this.fastForwardMs = fastForwardMs;
        this.repeatToggleModes = repeatToggleModes;
        this.showRewindButton = showRewindButton;
        this.showFastForwardButton = showFastForwardButton;
        this.showPreviousButton = showPreviousButton;
        this.showNextButton = showNextButton;
        this.showShuffleButton = showShuffleButton;
        this.showSubtitleButton = showSubtitleButton;
        this.showFullscreenButton = showFullscreenButton;
        this.showVideoSettingsButton = showVideoSettingsButton;
        this.animationEnabled = animationEnabled;
    }

    static PlayerAttributes createDefault() {
        return new PlayerAttributes(
                PlayerView.SURFACE_TYPE_SURFACE_VIEW,
                true,
                /* resizeMode */AspectRatioFrameLayout.RESIZE_MODE_FIT,
                /* controllerTimeout */PlayerControlView.DEFAULT_SHOW_TIMEOUT_MS,
                /* hideOnTouch */true,
                /* autoShowController */true,
                /* showBuffering */PlayerView.SHOW_BUFFERING_WHEN_PLAYING,
                /* useController */true,
                /* hideDuringAds */true,
                /* isDebugMode */false,
                /* rewindMs */DefaultControlDispatcher.DEFAULT_REWIND_MS,
                /* fastForwardMs */DefaultControlDispatcher.DEFAULT_REWIND_MS,
                /* repeatToggleModes */RepeatModeUtil.REPEAT_TOGGLE_MODE_ONE,
                /* showRewindButton */true,
                /* showFastForwardButton */true,
                /* showPreviousButton */true,
                /* showNextButton */true,
                /* showShuffleButton */false,
                /* showSubtitleButton */true,
                /* showFullscreenButton */false,
                /* showVideoSettingsButton */ false,
                /* animationEnabled */true
        );
    }

    public int getSurfaceType() {
        return surfaceType;
    }

    public int getTimeBarMinUpdateIntervalMs() {
        return timeBarMinUpdateIntervalMs;
    }

    public int getShowTimeoutMs() {
        return showTimeoutMs;
    }

    public long getHideAtMs() {
        return hideAtMs;
    }

    public boolean getUseArtWork() {
        return useArtWork;
    }

    public int getResizeMode() {
        return resizeMode;
    }

    public int getControllerTimeout() {
        return controllerTimeout;
    }

    public boolean getHideOnTouch() {
        return hideOnTouch;
    }

    public boolean getAutoShowController() {
        return autoShowController;
    }

    public int getShowBuffering() {
        return showBuffering;
    }

    public boolean getUseController() {
        return useController;
    }

    public boolean getHideDuringAds() {
        return hideDuringAds;
    }

    public boolean isDebugMode() {
        return isDebugMode;
    }

    public int getRewindMs() {
        return rewindMs;
    }

    public int getFastForwardMs() {
        return fastForwardMs;
    }

    public int getRepeatToggleModes() {
        return repeatToggleModes;
    }

    public boolean getShowRewindButton() {
        return showRewindButton;
    }

    public boolean getShowFastForwardButton() {
        return showFastForwardButton;
    }

    public boolean getShowPreviousButton() {
        return showPreviousButton;
    }

    public boolean getShowNextButton() {
        return showNextButton;
    }

    public boolean getShowShuffleButton() {
        return showShuffleButton;
    }

    public boolean getShowSubtitleButton() {
        return showSubtitleButton;
    }

    public boolean getAnimationEnabled() {
        return animationEnabled;
    }

    public boolean getShowFullscreenButton() { return showFullscreenButton; }

    public boolean getShowVideoSettingsButton() { return showVideoSettingsButton; }
}
