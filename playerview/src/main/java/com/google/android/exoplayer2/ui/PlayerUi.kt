package com.google.android.exoplayer2.ui

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.DefaultControlDispatcher
import com.google.android.exoplayer2.util.RepeatModeUtil

/**
 * A data class representing player styles and attributes to customize player's
 * behaviour and appearance.
 */
data class PlayerStyle(
    val isDebugMode: Boolean = false,
    val surfaceType: Int = PlayerView.SURFACE_TYPE_SURFACE_VIEW,
    val useArtWork: Boolean = true,
    val resizeMode: Int = AspectRatioFrameLayout.RESIZE_MODE_FIT,
    val timeBarMinUpdateIntervalMs: Int = StyledPlayerControlView.DEFAULT_TIME_BAR_MIN_UPDATE_INTERVAL_MS,
    val controlsTimeoutMs: Int = StyledPlayerControlView.DEFAULT_SHOW_TIMEOUT_MS,
    val hideAtMs: Long = C.TIME_UNSET,
    val hideOnTouch: Boolean = true,
    val autoShowController: Boolean = true,
    val showBuffering: Int = PlayerView.SHOW_BUFFERING_WHEN_PLAYING,
    val useController: Boolean = true,
    val hideDuringAds: Boolean = true,
    val rewindMs: Int = DefaultControlDispatcher.DEFAULT_REWIND_MS,
    val fastForwardMs: Int = DefaultControlDispatcher.DEFAULT_FAST_FORWARD_MS,
    val repeatToggleModes: Int = RepeatModeUtil.REPEAT_TOGGLE_MODE_NONE,
    val showRewindButton: Boolean = true,
    val showFastForwardButton: Boolean = true,
    val showPreviousButton: Boolean = true,
    val showNextButton: Boolean = true,
    val showShuffleButton: Boolean = false,
    val showSubtitleButton: Boolean = true,
    val showFullscreenButton: Boolean = true,
    val showVideoSettingsButton: Boolean = true,
    val animationEnabled: Boolean = true,
)

/**
 * A data class representing progress-bar styles and attributes to customize progress-bar's
 * behaviour and appearance.
 */
data class ProgressBarStyle(
    val scrubberDrawable: Drawable? = null,
    val barHeight: Int = DefaultTimeBar.DEFAULT_BAR_HEIGHT_DP,
    val touchTargetHeight: Int = DefaultTimeBar.DEFAULT_TOUCH_TARGET_HEIGHT_DP,
    val barGravity: Int = DefaultTimeBar.BAR_GRAVITY_CENTER,
    val adMarkerWidth: Int = DefaultTimeBar.DEFAULT_AD_MARKER_WIDTH_DP,
    val scrubberEnabledSize: Int = DefaultTimeBar.DEFAULT_SCRUBBER_ENABLED_SIZE_DP,
    val scrubberDisabledSize: Int = DefaultTimeBar.DEFAULT_SCRUBBER_DISABLED_SIZE_DP,
    val scrubberDraggedSize: Int = DefaultTimeBar.DEFAULT_SCRUBBER_DRAGGED_SIZE_DP,
    val playedColor: Int = DefaultTimeBar.DEFAULT_PLAYED_COLOR,
    val scrubberColor: Int = DefaultTimeBar.DEFAULT_SCRUBBER_COLOR,
    val bufferedColor: Int = DefaultTimeBar.DEFAULT_BUFFERED_COLOR,
    val unPlayedColor: Int = DefaultTimeBar.DEFAULT_UNPLAYED_COLOR,
    val adMarkerColor: Int = DefaultTimeBar.DEFAULT_AD_MARKER_COLOR,
    val playedAdMarkerColor: Int = DefaultTimeBar.DEFAULT_PLAYED_AD_MARKER_COLOR,
)

/**
 * A data class representing a MediaButton having `id`, `icon` and `contentDescription`.
 * [contentDescription] is used for accessibility purposes.
 */
data class MediaButton(
    val id: Int = View.NO_ID,
    val icon: Drawable? = null,
    val contentDescription: String? = null,
)

/**
 * An object containing all constants related to UI.
 */
object UiConstants {
    const val BUTTON_ENABLED_ALPHA = 1f
    const val BUTTON_DISABLED_ALPHA = .33f
    const val TIME_PLACEHOLDER = "00:00:00"
    const val TIME_SEPARATOR = "·"
}

/**
 * The colors used in [PlayerView] and [StyledPlayerView].
 */
object ExoColors {
    val WHITE = Color.parseColor("#ffffff")
    val WHITE_ALPHA_70 = Color.parseColor("#B3ffffff")
    val BLACK_ALPHA_70 = Color.parseColor("#B3000000")
    val BLACK_ALPHA_80 = Color.parseColor("#CC000000")
    val BLACK_ALPHA_60 = Color.parseColor("#98000000")
    val ERROR_MESSAGE_BG = Color.parseColor("#80808080")
    val BOTTOM_BAR_BG = GradientDrawable(
        GradientDrawable.Orientation.BOTTOM_TOP,
        intArrayOf(Color.BLACK, Color.TRANSPARENT)
    )
}

object ExoDimensions {
    val SETTINGS_WIDTH = 150.dp
    val SETTINGS_HEIGHT = 52.dp
    val SETTINGS_ICON_SIZE = 24.dp
    const val SETTINGS_TEXT_SIZE = 24
    const val SETTINGS_MAIN_TEXT_SIZE = 14f
    const val SETTINGS_SUB_TEXT_SIZE = 12f
    val SETTINGS_OFFSET = 8.dp

    val ICON_SIZE = 52.dp
    val ICON_MARGIN_X = 5.dp
    val ICON_PADDING = 2.dp
    const val ICON_TEXT_SIZE = 9f
    val ICON_PADDING_BOTTOM = 18.dp

    val SMALL_ICON_SIZE = 48.dp
    val SMALL_ICON_MARGIN_X = 2.dp
    val SMALL_ICON_PADDING = 12.dp

    val STYLED_PROGRESS_BAR_HEIGHT = 2.dp
    val STYLED_PROGRESS_ENABLED_THUMB_SIZE = 10.dp
    val STYLED_PROGRESS_DRAGGED_THUMB_SIZE = 14.dp
    val STYLED_PROGRESS_LAYOUT_HEIGHT = 48.dp
    val STYLED_PROGRESS_TOUCH_TARGET_HEIGHT = 48.dp
    val STYLED_PROGRESS_MARGIN_BOTTOM = 52.dp
    val STYLED_BOTTOM_BAR_HEIGHT = 60.dp
    val STYLED_BOTTOM_BAR_TIME_PADDING = 10.dp
    val STYLED_BOTTOM_BAR_MARGIN_TOP = 10.dp
    val STYLED_CONTROLS_PADDING = 24.dp
    val STYLED_MINIMAL_CONTROLS_MARGIN_BOTTOM = 4.dp

    val ERROR_HEIGHT = 32.dp
    val ERROR_MARGIN_BOTTOM = 64.dp
    const val ERROR_TEXT_SIZE = 14f
    val ERROR_TEXT_PADDING_X = 12.dp
    val ERROR_TEXT_PADDING_Y = 4.dp
}

/**
 * List of [MediaButton]s for [PlayerView].
 */
fun Context.playerViewButtons(debugMode: Boolean = false) = listOf(
    MediaButton(
        R.id.exo_prev,
        getIcon(R.string.exo_controls_previous, debugMode),
        "Previous"
    ),
    MediaButton(
        R.id.exo_repeat_toggle,
        getIcon(R.string.exo_controls_repeat_off, debugMode),
        "Repeat"
    ),
    MediaButton(
        R.id.exo_rew,
        getIcon(R.string.exo_controls_rewind, debugMode),
        "Rewind"
    ),
    MediaButton(
        R.id.exo_play,
        getIcon(R.string.exo_controls_play, debugMode),
        "Play"
    ),
    MediaButton(
        R.id.exo_pause,
        getIcon(R.string.exo_controls_pause, debugMode),
        "Pause"
    ),
    MediaButton(
        R.id.exo_ffwd,
        getIcon(R.string.exo_controls_fastforward, debugMode),
        "Forward"
    ),
    MediaButton(
        R.id.exo_shuffle,
        getIcon(R.string.exo_controls_shuffle_off, debugMode),
        "Shuffle playlist"
    ),
    MediaButton(
        R.id.exo_next,
        getIcon(R.string.exo_controls_next, debugMode),
        "Next"
    ),
    MediaButton(
        R.id.exo_vr,
        getIcon(R.string.exo_controls_vr, debugMode),
        "VR mode"
    ),
)

/**
 * List of Center [MediaButton]s for [StyledPlayerControlView].
 */
fun Context.centerButtons(debugMode: Boolean = false) = listOf(
    MediaButton(
        R.id.exo_prev,
        getIcon(R.string.exo_controls_previous, debugMode),
        "Previous"
    ),
    MediaButton(
        R.id.exo_rew,
        getIcon(R.string.exo_controls_rewind, debugMode),
        "Rewind"
    ),
    MediaButton(
        R.id.exo_play_pause,
        getIcon(R.string.exo_controls_play, debugMode),
        "Play"
    ),
    MediaButton(
        R.id.exo_ffwd,
        getIcon(R.string.exo_controls_fastforward, debugMode),
        "Forward"
    ),
    MediaButton(
        R.id.exo_next,
        getIcon(R.string.exo_controls_next, debugMode),
        "Next"
    ),
)

/**
 * List of Bottom [MediaButton]s for [StyledPlayerControlView].
 */
fun Context.bottomButtons(debugMode: Boolean = false) = listOf(
    MediaButton(
        R.id.exo_vr,
        getIcon(R.string.exo_controls_vr, debugMode),
        "VR mode"
    ),
    MediaButton(
        R.id.exo_shuffle,
        getIcon(R.string.exo_controls_shuffle_off, debugMode),
        "Shuffle playlist"
    ),
    MediaButton(
        R.id.exo_repeat_toggle,
        getIcon(R.string.exo_controls_repeat_off, debugMode),
        "Repeat"
    ),
    MediaButton(
        R.id.exo_subtitle,
        getIcon(R.string.exo_controls_subtitle_off, debugMode),
        "Toggle subtitles"
    ),
    MediaButton(
        R.id.exo_settings,
        getIcon(R.string.exo_controls_settings, debugMode),
        "Open settings"
    ),
    MediaButton(
        R.id.exo_video_settings,
        getIcon(R.string.exo_controls_video_settings, debugMode),
        "Open videos settings"
    ),
    MediaButton(
        R.id.exo_fullscreen,
        getIcon(R.string.exo_controls_fullscreen_enter, debugMode),
        "Toggle fullscreen"
    ),
    MediaButton(
        R.id.exo_overflow_show,
        getIcon(R.string.exo_controls_overflow_show, debugMode),
        "Show additional settings"
    ),
)

/**
 * List of Overflow [MediaButton]s for [StyledPlayerControlView].
 */
fun Context.overflowButtons(debugMode: Boolean = false) = listOf(
    MediaButton(
        R.id.exo_overflow_hide,
        getIcon(R.string.exo_controls_overflow_hide, debugMode),
        "Hide additional settings"
    ),
)

/**
 * List of Minimal [MediaButton]s for [StyledPlayerControlView].
 */
fun Context.minimalButtons(debugMode: Boolean = false) = listOf(
    MediaButton(
        R.id.exo_minimal_fullscreen,
        getIcon(R.string.exo_controls_fullscreen_enter, debugMode),
        "Toggle fullscreen"
    ),
)

fun settingsListItem(
    context: Context
): LinearLayout {
    return context.listTile(
        style = ListTileStyle(
            leadingSize = ExoDimensions.SETTINGS_ICON_SIZE,
            height = ViewGroup.LayoutParams.WRAP_CONTENT,
        ),
        modifier = {
            minimumHeight = ExoDimensions.SETTINGS_HEIGHT
            minimumWidth = ExoDimensions.SETTINGS_WIDTH
        },
        leading = {
            image {
                id = R.id.exo_settings_item_icon
            }
        },
        title = {
            textView {
                id = R.id.exo_settings_item_title
                setTextSize(
                    TypedValue.COMPLEX_UNIT_SP,
                    ExoDimensions.SETTINGS_MAIN_TEXT_SIZE
                )
                setTextColor(ExoColors.WHITE)
                layoutParams = linearLayoutParams()
            }
        },
        subtitle = {
            textView {
                id = R.id.exo_settings_item_subtitle
                setTextSize(
                    TypedValue.COMPLEX_UNIT_SP,
                    ExoDimensions.SETTINGS_SUB_TEXT_SIZE
                )
                setTextColor(ExoColors.WHITE_ALPHA_70)
                layoutParams = linearLayoutParams()
            }
        }
    )
}

fun settingsSubListItem(
    context: Context,
    debugMode: Boolean = false,
): LinearLayout {
    return context.listTile(
        style = ListTileStyle(
            leadingSize = ExoDimensions.SETTINGS_ICON_SIZE,
            height = ViewGroup.LayoutParams.WRAP_CONTENT,
        ),
        modifier = {
            minimumHeight = 48.dp
            minimumWidth = ExoDimensions.SETTINGS_WIDTH
        },
        leading = {
            image {
                id = R.id.exo_settings_item_icon
                setImageDrawable(context.getIcon(R.string.exo_controls_check, debugMode))
                visibility = View.INVISIBLE
            }
        },
        title = {
            textView {
                id = R.id.exo_settings_item_title
                setTextSize(
                    TypedValue.COMPLEX_UNIT_SP,
                    ExoDimensions.SETTINGS_MAIN_TEXT_SIZE
                )
                setTextColor(ExoColors.WHITE)
                layoutParams = linearLayoutParams()
            }
        },
    )
}

/**
 * Create simple player view.
 */
fun simplePlayer(
    root: ViewGroup,
    playerStyle: PlayerStyle = PlayerStyle(),
    progressBarStyle: ProgressBarStyle = ProgressBarStyle(),
): FrameLayout {
    return playerBase(
        root,
        controls = {
            simpleControls(
                playerStyle = playerStyle,
                progressBarStyle = progressBarStyle,
            )
        }
    )
}

fun simpleControls(
    root: ViewGroup,
    progressBarStyle: ProgressBarStyle = ProgressBarStyle(),
    buttons: List<MediaButton> = emptyList(),
): LinearLayout {
    val buttonSize = 56
    val textSize = 14f
    val textColor = ExoColors.WHITE
    root.apply {
        id = R.id.exo_controller
        layoutParams = frameLayoutParams(matchParent, matchParent)
    }
    val controller = root.column(
        modifier = {
            layoutParams = frameLayoutParams(matchParent, wrapContent, Gravity.BOTTOM.or(Gravity.CENTER))
            layoutDirection = View.LAYOUT_DIRECTION_LTR
            background = GradientDrawable(
                GradientDrawable.Orientation.BOTTOM_TOP,
                intArrayOf(Color.BLACK, Color.TRANSPARENT)
            )
        }
    ) {
        listOf(
            row(
                modifier = {
                    layoutParams = linearLayoutParams(matchParent, matchParent)
                    setPadding(0, 4.dp, 0, 0)
                    gravity = Gravity.CENTER
                }
            ) {
                buttons.map {
                    imageButton {
                        id = it.id
                        scaleType = ImageView.ScaleType.FIT_XY
                        setImageDrawable(it.icon)
                        contentDescription = it.contentDescription
                        layoutParams = linearLayoutParams(
                            buttonSize.dp,
                            buttonSize.dp
                        ) {
                            margin = 2.dp
                        }
                        padding(12.dp)
                        applySelectableBackground()
                    }
                }
            },
            row(
                modifier = {
                    layoutParams = linearLayoutParams(
                        matchParent,
                        matchParent
                    ) {
                        setMargins(0, 4.dp, 0, 0)
                    }
                    gravity = Gravity.CENTER
                }
            ) {
                val textStyle: TextView.() -> Unit = {
                    layoutParams = linearLayoutParams(gravity = Gravity.CENTER_VERTICAL)
                    setPadding(4.dp, 0, 4.dp, 0)
                    includeFontPadding = false
                    setTypeface(typeface, Typeface.BOLD)
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
                    gravity = Gravity.CENTER
                    setTextColor(textColor)
                }
                listOf(
                    textView {
                        id = R.id.exo_position
                        textStyle()
                    },
                    timeBar(
                        style = progressBarStyle,
                    ) {
                        id = R.id.exo_time
                        layoutParams = linearLayoutParams(0, 26.dp, Gravity.CENTER_VERTICAL, 1f)
                    },
                    textView {
                        id = R.id.exo_duration
                        textStyle()
                    },
                )
            }
        )
    }
    root.addView(controller)
    return controller
}

fun styledPlayer(
    root: ViewGroup,
    playerStyle: PlayerStyle = PlayerStyle(),
    progressBarStyle: ProgressBarStyle = ProgressBarStyle(),
): FrameLayout {
    return playerBase(
        root,
        controls = {
            styledControls(
                playerStyle = playerStyle,
                progressBarStyle = progressBarStyle,
            )
        }
    )
}

fun styledControls(
    root: ViewGroup,
    progressBarStyle: ProgressBarStyle = ProgressBarStyle(),
    bottomButtons: List<MediaButton> = emptyList(),
    overflowControls: List<MediaButton> = emptyList(),
    minimalControls: List<MediaButton> = emptyList(),
    centerControls: List<MediaButton> = emptyList(),
): FrameLayout {

    val timeTextStyles: TextView.() -> Unit = {
        layoutParams = linearLayoutParams(gravity = Gravity.CENTER_VERTICAL)
        setPadding(4.dp, 0, 4.dp, 0)
        includeFontPadding = false
        setTypeface(typeface, Typeface.BOLD)
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        gravity = Gravity.CENTER
    }

    val bottomBarButtonStyles: ImageButton.() -> Unit = {
        applySelectableBackground(true)
        scaleType = ImageView.ScaleType.FIT_XY
        layoutParams = linearLayoutParams(
            ExoDimensions.SMALL_ICON_SIZE,
            ExoDimensions.SMALL_ICON_SIZE,
            Gravity.CENTER_HORIZONTAL
        ) {
            horizontalMargin = ExoDimensions.SMALL_ICON_MARGIN_X
            padding(ExoDimensions.SMALL_ICON_PADDING)
        }
    }
    val centerButtonStyles: ImageButton.() -> Unit = {
        applySelectableBackground(true)
        scaleType = ImageView.ScaleType.FIT_XY
        layoutParams = linearLayoutParams(
            ExoDimensions.ICON_SIZE,
            ExoDimensions.ICON_SIZE,
            Gravity.CENTER_HORIZONTAL
        ) {
            horizontalMargin = ExoDimensions.ICON_MARGIN_X
            padding(ExoDimensions.ICON_PADDING)
        }
    }

    root.apply {
        id = R.id.exo_controller
        layoutParams = frameLayoutParams(matchParent, matchParent)
    }
    val controls = root.box(
        modifier = {
            layoutParams = frameLayoutParams(matchParent, matchParent)
        }
    ) {
        listOf(

            // Controls Background
            view(
                modifier = {
                    id = R.id.exo_controls_background
                    layoutParams = frameLayoutParams(0, 0)
                    setBackgroundColor(ExoColors.BLACK_ALPHA_60)
                }
            ),

            // Bottom Controls Bar
            box(
                modifier = {
                    id = R.id.exo_bottom_bar
                    layoutParams = frameLayoutParams(
                        matchParent,
                        ExoDimensions.STYLED_BOTTOM_BAR_HEIGHT,
                        Gravity.BOTTOM,
                    ) {
                        setMargins(ExoDimensions.STYLED_BOTTOM_BAR_MARGIN_TOP, 0, 0, 0)
                        background = ExoColors.BOTTOM_BAR_BG
                        layoutDirection = View.LAYOUT_DIRECTION_LTR
                    }
                }
            ) {
                listOf(
                    // Exo Time (Duration & Position)
                    row(
                        modifier = {
                            id = R.id.exo_time
                            layoutParams = frameLayoutParams(
                                gravity = Gravity.CENTER_VERTICAL.or(Gravity.START)
                            )
                            layoutDirection = View.LAYOUT_DIRECTION_LTR
                            padding(ExoDimensions.STYLED_BOTTOM_BAR_TIME_PADDING)
                        }
                    ) {
                        listOf(
                            textView {
                                id = R.id.exo_position
                                timeTextStyles()
                                setTextColor(ExoColors.WHITE)
                                text = UiConstants.TIME_PLACEHOLDER
                            },
                            textView {
                                timeTextStyles()
                                setTextColor(ExoColors.WHITE)
                                text = UiConstants.TIME_SEPARATOR
                            },
                            textView {
                                id = R.id.exo_duration
                                timeTextStyles()
                                setTextColor(ExoColors.WHITE_ALPHA_70)
                                text = UiConstants.TIME_PLACEHOLDER
                            },
                        )
                    },

                    // Controls
                    row(
                        modifier = {
                            id = R.id.exo_basic_controls
                            layoutParams = frameLayoutParams(
                                gravity = Gravity.CENTER_VERTICAL.or(Gravity.END)
                            )
                            layoutDirection = View.LAYOUT_DIRECTION_LTR
                        }
                    ) {
                        bottomButtons.map {
                            imageButton(
                                modifier = {
                                    bottomBarButtonStyles()
                                    id = it.id
                                    contentDescription = it.contentDescription
                                    setImageDrawable(it.icon)
                                }
                            )
                        }
                    },

                    // Extra Controls
                    scrollableRow(
                        modifier = {
                            id = R.id.exo_extra_controls_scroll_view
                            layoutParams = frameLayoutParams(
                                gravity = Gravity.CENTER_VERTICAL.or(Gravity.END)
                            )
                            visibility = View.INVISIBLE
                        }
                    ) {
                        listOf(
                            row(
                                modifier = {
                                    id = R.id.exo_extra_controls
                                    layoutParams = linearLayoutParams()
                                    layoutDirection = View.LAYOUT_DIRECTION_LTR
                                }
                            ) {
                                overflowControls.map {
                                    imageButton(
                                        modifier = {
                                            bottomBarButtonStyles()
                                            id = it.id
                                            contentDescription = it.contentDescription
                                            setImageDrawable(it.icon)
                                        }
                                    )
                                }
                            }
                        )
                    }
                )
            },

            // Progress Bar
            timeBar(
                style = progressBarStyle,
                modifier = {
                    id = R.id.exo_progress
                    layoutParams = frameLayoutParams(
                        matchParent,
                        wrapContent,
                        Gravity.BOTTOM
                    ) {
                        setMargins(0, 0, 0, ExoDimensions.STYLED_PROGRESS_MARGIN_BOTTOM)
                    }
                }
            ),

            // Minimal Controls
            row(
                modifier = {
                    id = R.id.exo_minimal_controls
                    layoutParams = frameLayoutParams(
                        gravity = Gravity.BOTTOM.or(Gravity.END)
                    ) {
                        setMargins(0, 0, 0, ExoDimensions.STYLED_MINIMAL_CONTROLS_MARGIN_BOTTOM)
                    }
                    gravity = Gravity.CENTER_VERTICAL
                    layoutDirection = View.LAYOUT_DIRECTION_LTR
                }
            ) {
                minimalControls.map {
                    imageButton(
                        modifier = {
                            bottomBarButtonStyles()
                            id = it.id
                            contentDescription = it.contentDescription
                            setImageDrawable(it.icon)
                        }
                    )
                }
            },

            // Center Controls
            row(
                modifier = {
                    id = R.id.exo_center_controls
                    layoutParams = frameLayoutParams(gravity = Gravity.CENTER)
                    setBackgroundColor(Color.TRANSPARENT)
                    gravity = Gravity.CENTER
                    padding(ExoDimensions.STYLED_CONTROLS_PADDING)
                }
            ) {
                centerControls.map {
                    imageButton(
                        modifier = {
                            centerButtonStyles()
                            id = it.id
                            contentDescription = it.contentDescription
                            setImageDrawable(it.icon)
                        }
                    )
                }
            }
        )
    }
    root.addView(controls)
    return controls
}

/**
 * Creates a base layout for player view. It can easily be extended to make [PlayerView]
 * and [StyledPlayerView] by providing custom controls.
 */
fun playerBase(
    root: ViewGroup,
    controls: ViewGroup.() -> View,
): FrameLayout {

    val contentFrame = root.aspectRatio(
        modifier = {
            id = R.id.exo_content_frame
            layoutParams = frameLayoutParams(matchParent, matchParent, Gravity.CENTER)
        }
    ) {
        listOf(
            view {
                id = R.id.exo_shutter
                layoutParams = frameLayoutParams(matchParent, matchParent, Gravity.CENTER)
                setBackgroundColor(Color.BLACK)
            },
            image {
                id = R.id.exo_artwork
                layoutParams = frameLayoutParams(matchParent, matchParent)
                scaleType = ImageView.ScaleType.FIT_XY
            },
            subtitleView {
                id = R.id.exo_subtitles
                layoutParams = frameLayoutParams(matchParent, matchParent)
                setUserDefaultStyle()
                setUserDefaultTextSize()
            },
            circularProgress {
                id = R.id.exo_buffering
                layoutParams = frameLayoutParams(gravity = Gravity.CENTER)
                isIndeterminate = true
                visibility = View.GONE
                indeterminateTintList = ColorStateList.valueOf(ExoColors.WHITE_ALPHA_70)
            },
            textView {
                id = R.id.exo_error_message
                layoutParams = frameLayoutParams(
                    matchParent,
                    ExoDimensions.ERROR_HEIGHT,
                    Gravity.CENTER
                ) {
                    setMargins(0, 0, 0, ExoDimensions.STYLED_PROGRESS_MARGIN_BOTTOM)
                }
                gravity = Gravity.CENTER
                padding(ExoDimensions.ERROR_TEXT_PADDING_X, ExoDimensions.ERROR_TEXT_PADDING_Y)
                visibility = View.GONE
                setBackgroundColor(ExoColors.ERROR_MESSAGE_BG)
                setTextSize(TypedValue.COMPLEX_UNIT_SP, ExoDimensions.ERROR_TEXT_SIZE)
            }
        )
    }

    val contentWithControls = root.box {
        listOf(
            contentFrame,
            box(
                modifier = {
                    id = R.id.exo_ad_overlay
                    layoutParams = frameLayoutParams(matchParent, matchParent)
                }
            ),
            box(
                modifier = {
                    id = R.id.exo_overlay
                    layoutParams = frameLayoutParams(matchParent, matchParent)
                }
            ),
            controls()
        )
    }
    root.addView(contentWithControls)
    return contentWithControls
}