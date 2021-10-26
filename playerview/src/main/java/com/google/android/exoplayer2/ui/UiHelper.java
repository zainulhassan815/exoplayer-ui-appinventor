package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.io.FileInputStream;
import java.io.InputStream;

public class UiHelper {

    public static final String LOG_TAG = "ExoplayerUi";
    public static final String IC_PLAY = "ic_play.png";
    public static final String IC_PAUSE = "ic_pause.png";
    public static final String IC_NEXT = "ic_next.png";
    public static final String IC_PREVIOUS = "ic_previous.png";
    public static final String IC_FAST_FORWARD = "ic_fast_forward.png";
    public static final String IC_REWIND = "ic_rewind.png";
    public static final String IC_REPEAT_OFF = "ic_repeat_off.png";
    public static final String IC_REPEAT_ONE = "ic_repeat_one.png";
    public static final String IC_REPEAT_ALL = "ic_repeat_all.png";
    public static final String IC_SHUFFLE_ON = "ic_shuffle_on.png";
    public static final String IC_SHUFFLE_OFF = "ic_shuffle_off.png";
    public static final String IC_SUBTITLE_ON = "ic_subtitle_on.png";
    public static final String IC_SUBTITLE_OFF = "ic_subtitle_off.png";
    public static final String IC_SETTINGS = "ic_settings.png";
    public static final String IC_VIDEO_SETTINGS = "ic_video_settings.png";
    public static final String IC_SPEED = "ic_speed.png";
    public static final String IC_AUDIO_TRACK = "ic_audio_track.png";
    public static final String IC_FULLSCREEN_ENTER = "ic_fullscreen_enter.png";
    public static final String IC_FULLSCREEN_EXIT = "ic_fullscreen_exit.png";
    public static final String IC_CHECK = "ic_check.png";
    public static final String INITIAL_DURATION = "00:00";
    public static final int CONTROLS_COLOR = Color.parseColor("#98000000");
    public static final int BOTTOM_BAR_COLOR = Color.parseColor("#b0000000");
    public static final int TRANSPARENT_BLACK = Color.parseColor("#CC000000");
    public static final int DIM_WHITE = Color.parseColor("#FFBEBEBE");
    public static final float BUTTON_ENABLED_ALPHA = 1f;
    public static final float BUTTON_DISABLED_ALPHA = .33f;

    public static void setSelectableBackground(View view, boolean borderless) {
        TypedValue outValue = new TypedValue();
        view.getContext().getTheme().resolveAttribute(borderless ? android.R.attr.selectableItemBackgroundBorderless : android.R.attr.selectableItemBackground, outValue, true);
        view.setBackgroundResource(outValue.resourceId);
    }

    @Nullable
    public static Drawable getDrawable(Context context, String fileName, boolean debugMode) {
        try {
            @Nullable
            InputStream inputStream = getAsset(context, fileName, debugMode);
            if (inputStream != null) {
                Drawable drawable = Drawable.createFromStream(inputStream, null);
                inputStream.close();
                return drawable;
            }
            return null;
        } catch (Exception e) {
            Log.v(LOG_TAG, "getDrawable : Error = " + e);
            return null;
        }
    }

    @Nullable
    public static InputStream getAsset(Context context, String file, boolean debugMode) {
        try {
            if (debugMode) {
                String path;
                if (context.getClass().getName().contains("makeroid")) {
                    if (Build.VERSION.SDK_INT >= 29) {
                        path = context.getExternalFilesDir(null).toString() + "/assets/" + file;
                    } else {
                        path = "/storage/emulated/0/Kodular/assets/" + file;
                    }
                } else {
                    path = context.getExternalFilesDir(null).toString() + "/AppInventor/assets/" + file;
                }
                Log.v(LOG_TAG, "getAsset | Filepath = " + path);
                return new FileInputStream(path);
            } else {
                return context.getAssets().open(file);
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "getAsset | Debug Mode : " + debugMode + " | Error : " + e);
            return null;
        }
    }

    public static int convertToDp(float dip) {
        DisplayMetrics displayMetric = Resources.getSystem().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, displayMetric);
    }

    public static ImageButton createCenterImageButton(Context context, @Nullable Drawable drawable) {
        ImageButton button = new ImageButton(context);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(convertToDp(50), convertToDp(50));
        int margin = convertToDp(4);
        params.setMargins(margin, 0, margin, 0);
        button.setLayoutParams(params);
        button.setImageDrawable(drawable);
        button.setScaleType(ImageView.ScaleType.FIT_CENTER);
        int padding = convertToDp(6);
        button.setPadding(padding, padding, padding, padding);
        setSelectableBackground(button, true);
        return button;
    }

    public static ImageButton createBottomImageButton(Context context, @Nullable Drawable drawable) {
        ImageButton button = new ImageButton(context);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(convertToDp(48), convertToDp(48));
        int margin = convertToDp(2);
        params.setMargins(margin, 0, margin, 0);
        button.setLayoutParams(params);
        button.setImageDrawable(drawable);
        button.setScaleType(ImageView.ScaleType.FIT_CENTER);
        int padding = convertToDp(12);
        button.setPadding(padding, padding, padding, padding);
        setSelectableBackground(button, true);
        return button;
    }

}
