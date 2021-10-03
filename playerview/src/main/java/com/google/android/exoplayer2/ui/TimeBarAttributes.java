package com.google.android.exoplayer2.ui;

import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;

public class TimeBarAttributes {

    @Nullable
    private final Drawable scrubberDrawable;
    private final int barHeight;
    private final int touchTargetHeight;
    private final int barGravity;
    private final int adMarkerWidth;
    private final int scrubberEnabledSize;
    private final int scrubberDisabledSize;
    private final int scrubberDraggedSize;
    private final int playedColor;
    private final int scrubberColor;
    private final int bufferedColor;
    private final int unplayedColor;
    private final int adMarkerColor;
    private final int playedAdMarkerColor;

    public TimeBarAttributes(@Nullable Drawable scrubberDrawable, int barHeight, int touchTargetHeight, int barGravity, int adMarkerWidth, int scrubberEnabledSize, int scrubberDisabledSize, int scrubberDraggedSize, int playedColor, int scrubberColor, int bufferedColor, int unplayedColor, int adMarkerColor, int playedAdMarkerColor) {
        this.scrubberDrawable = scrubberDrawable;
        this.barHeight = barHeight;
        this.touchTargetHeight = touchTargetHeight;
        this.barGravity = barGravity;
        this.adMarkerWidth = adMarkerWidth;
        this.scrubberEnabledSize = scrubberEnabledSize;
        this.scrubberDisabledSize = scrubberDisabledSize;
        this.scrubberDraggedSize = scrubberDraggedSize;
        this.playedColor = playedColor;
        this.scrubberColor = scrubberColor;
        this.bufferedColor = bufferedColor;
        this.unplayedColor = unplayedColor;
        this.adMarkerColor = adMarkerColor;
        this.playedAdMarkerColor = playedAdMarkerColor;
    }

    public static TimeBarAttributes createDefault() {
        return new TimeBarAttributes(
                null,
                DefaultTimeBar.DEFAULT_BAR_HEIGHT_DP,
                DefaultTimeBar.DEFAULT_TOUCH_TARGET_HEIGHT_DP,
                DefaultTimeBar.BAR_GRAVITY_CENTER,
                DefaultTimeBar.DEFAULT_AD_MARKER_WIDTH_DP,
                DefaultTimeBar.DEFAULT_SCRUBBER_ENABLED_SIZE_DP,
                DefaultTimeBar.DEFAULT_SCRUBBER_DISABLED_SIZE_DP,
                DefaultTimeBar.DEFAULT_SCRUBBER_DRAGGED_SIZE_DP,
                DefaultTimeBar.DEFAULT_PLAYED_COLOR,
                DefaultTimeBar.DEFAULT_SCRUBBER_COLOR,
                DefaultTimeBar.DEFAULT_BUFFERED_COLOR,
                DefaultTimeBar.DEFAULT_UNPLAYED_COLOR,
                DefaultTimeBar.DEFAULT_AD_MARKER_COLOR,
                DefaultTimeBar.DEFAULT_PLAYED_AD_MARKER_COLOR
        );
    }

    @Nullable
    public Drawable getScrubberDrawable() {
        return scrubberDrawable;
    }

    public int getBarHeight() {
        return barHeight;
    }

    public int getTouchTargetHeight() {
        return touchTargetHeight;
    }

    public int getBarGravity() {
        return barGravity;
    }

    public int getAdMarkerWidth() {
        return adMarkerWidth;
    }

    public int getScrubberEnabledSize() {
        return scrubberEnabledSize;
    }

    public int getScrubberDisabledSize() {
        return scrubberDisabledSize;
    }

    public int getScrubberDraggedSize() {
        return scrubberDraggedSize;
    }

    public int getPlayedColor() {
        return playedColor;
    }

    public int getScrubberColor() {
        return scrubberColor;
    }

    public int getBufferedColor() {
        return bufferedColor;
    }

    public int getUnplayedColor() {
        return unplayedColor;
    }

    public int getAdMarkerColor() {
        return adMarkerColor;
    }

    public int getPlayedAdMarkerColor() {
        return playedAdMarkerColor;
    }
}
