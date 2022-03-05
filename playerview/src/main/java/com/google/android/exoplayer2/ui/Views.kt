package com.google.android.exoplayer2.ui

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*

fun View.applySelectableBackground(borderless: Boolean = false) {
    val id =
        if (borderless) android.R.attr.selectableItemBackgroundBorderless else android.R.attr.selectableItemBackground
    val value = context.resolverAttribute(id)
    setBackgroundResource(value.resourceId)
}

private fun Context.resolverAttribute(id: Int): TypedValue {
    val outValue = TypedValue()
    theme.resolveAttribute(id, outValue, true)
    return outValue
}

inline fun Context.view(
    modifier: View.() -> Unit = {},
): View {
    return View(this).apply {
        modifier()
    }
}

fun Context.space(
    width: Int = 0,
    height: Int = 0,
): Space {
    return Space(this).apply {
        layoutParams = linearLayoutParams(width, height)
    }
}

fun View.space(
    width: Int = 0,
    height: Int = 0,
): Space {
    return context.space(width, height)
}

inline fun View.view(
    modifier: View.() -> Unit = {},
): View {
    return context.view(modifier)
}

inline fun Context.image(
    modifier: ImageView.() -> Unit = {},
): ImageView {
    return ImageView(this).apply(modifier)
}

inline fun View.image(
    modifier: ImageView.() -> Unit = {},
): ImageView {
    return context.image(modifier)
}

inline fun Context.imageButton(
    modifier: ImageButton.() -> Unit = {},
): ImageButton {
    return ImageButton(this).apply(modifier)
}

inline fun View.imageButton(
    modifier: ImageButton.() -> Unit = {},
): ImageButton {
    return context.imageButton(modifier)
}

inline fun Context.circularProgress(
    modifier: ProgressBar.() -> Unit = {},
): ProgressBar {
    return ProgressBar(this).apply(modifier)
}

inline fun View.circularProgress(
    modifier: ProgressBar.() -> Unit = {},
): ProgressBar {
    return context.circularProgress(modifier)
}

inline fun Context.textView(
    modifier: TextView.() -> Unit = {},
): TextView {
    return TextView(this).apply(modifier)
}

inline fun View.textView(
    modifier: TextView.() -> Unit = {},
): TextView {
    return context.textView(modifier)
}

inline fun Context.subtitleView(
    modifier: SubtitleView.() -> Unit = {},
): SubtitleView {
    return SubtitleView(this).apply(modifier)
}

inline fun View.subtitleView(
    modifier: SubtitleView.() -> Unit = {},
): SubtitleView {
    return context.subtitleView(modifier)
}

inline fun Context.timeBar(
    style: ProgressBarStyle = ProgressBarStyle(),
    modifier: DefaultTimeBar.() -> Unit = {},
): DefaultTimeBar {
    return DefaultTimeBar(this, style).apply(modifier)
}

inline fun View.timeBar(
    style: ProgressBarStyle = ProgressBarStyle(),
    modifier: DefaultTimeBar.() -> Unit = {},
): DefaultTimeBar {
    return context.timeBar(style, modifier)
}

inline fun Context.simpleControls(
    playerStyle: PlayerStyle = PlayerStyle(),
    progressBarStyle: ProgressBarStyle = ProgressBarStyle(),
    modifier: PlayerControlView.() -> Unit = {},
): PlayerControlView {
    return PlayerControlView(this, playerStyle, progressBarStyle).apply(modifier)
}

inline fun View.simpleControls(
    playerStyle: PlayerStyle = PlayerStyle(),
    progressBarStyle: ProgressBarStyle = ProgressBarStyle(),
    modifier: PlayerControlView.() -> Unit = {},
): PlayerControlView {
    return context.simpleControls(playerStyle, progressBarStyle, modifier)
}

inline fun Context.styledControls(
    playerStyle: PlayerStyle = PlayerStyle(),
    progressBarStyle: ProgressBarStyle = ProgressBarStyle(),
    modifier: StyledPlayerControlView.() -> Unit = {}
): StyledPlayerControlView {
    return StyledPlayerControlView(this, playerStyle, progressBarStyle).apply(modifier)
}

inline fun View.styledControls(
    playerStyle: PlayerStyle = PlayerStyle(),
    progressBarStyle: ProgressBarStyle = ProgressBarStyle(),
    modifier: StyledPlayerControlView.() -> Unit = {}
): StyledPlayerControlView {
    return context.styledControls(playerStyle, progressBarStyle, modifier)
}

data class ListTileStyle(
    val height: Int = 56.dp,
    val horizontalPadding: Int = 8.dp,
    val verticalPadding: Int = 8.dp,
    val leadingSize: Int = 48.dp,
    val trailingSize: Int = 48.dp,
    val contentSpacing: Int = 8.dp,
)

inline fun Context.listTile(
    style: ListTileStyle = ListTileStyle(),
    title: ViewGroup.() -> View,
    subtitle: ViewGroup.() -> View? = { null },
    leading: ViewGroup.() -> View? = { null },
    trailing: ViewGroup.() -> View? = { null },
    modifier: LinearLayout.() -> Unit = {},
): LinearLayout {
    return row(
        modifier = {
            layoutParams = linearLayoutParams(matchParent, style.height, Gravity.CENTER)
            padding(style.horizontalPadding, style.verticalPadding)
            applySelectableBackground()
            gravity = Gravity.START.or(Gravity.CENTER_VERTICAL)
            modifier()
        }
    ) {
        val leadingView = leading()?.apply {
            if (layoutParams == null) {
                layoutParams = linearLayoutParams(style.leadingSize, style.leadingSize)
            } else {
                layoutParams.height = style.leadingSize
                layoutParams.width = style.leadingSize
            }
            requestLayout()
        }
        val trailingView = trailing()?.apply {
            if (layoutParams == null) {
                layoutParams = linearLayoutParams(style.leadingSize, style.leadingSize)
            } else {
                layoutParams.height = style.leadingSize
                layoutParams.width = style.leadingSize
            }
            requestLayout()
        }
        listOfNotNull(
            leadingView,
            if (leadingView != null) space(style.contentSpacing) else null,
            column(
                modifier = {
                    layoutParams = linearLayoutParams(
                        0,
                        wrapContent,
                        gravity = Gravity.START.or(Gravity.CENTER_VERTICAL),
                        weight = 1f,
                    )
                    gravity = Gravity.START.or(Gravity.CENTER_VERTICAL)
                }
            ) {
                listOfNotNull(
                    title(),
                    subtitle()
                )
            },
            if (trailingView != null) space(style.contentSpacing) else null,
            trailingView,
        )
    }
}

inline fun View.listTile(
    style: ListTileStyle = ListTileStyle(),
    title: ViewGroup.() -> View,
    subtitle: ViewGroup.() -> View? = { null },
    leading: ViewGroup.() -> View? = { null },
    trailing: ViewGroup.() -> View? = { null },
    modifier: LinearLayout.() -> Unit = {},
): LinearLayout {
    return context.listTile(style, title, subtitle, leading, trailing, modifier)
}

inline fun Context.aspectRatio(
    modifier: AspectRatioFrameLayout.() -> Unit = {},
    content: ViewGroup.() -> List<View> = { emptyList() },
): AspectRatioFrameLayout {
    return AspectRatioFrameLayout(this)
        .apply(modifier)
        .apply {
            content().forEach(this::addView)
        }
}

inline fun View.aspectRatio(
    modifier: AspectRatioFrameLayout.() -> Unit = {},
    content: ViewGroup.() -> List<View> = { emptyList() },
): AspectRatioFrameLayout {
    return context.aspectRatio(modifier, content)
}

inline fun Context.row(
    modifier: LinearLayout.() -> Unit = {},
    content: ViewGroup.() -> List<View> = { emptyList() },
): LinearLayout {
    return LinearLayout(this)
        .apply(modifier)
        .apply {
            orientation = LinearLayout.HORIZONTAL
            content().forEach(this::addView)
        }
}

inline fun View.row(
    modifier: LinearLayout.() -> Unit = {},
    content: ViewGroup.() -> List<View> = { emptyList() },
): LinearLayout {
    return context.row(modifier, content)
}

inline fun Context.column(
    modifier: LinearLayout.() -> Unit = {},
    content: ViewGroup.() -> List<View> = { emptyList() },
): LinearLayout {
    return LinearLayout(this)
        .apply(modifier)
        .apply {
            orientation = LinearLayout.VERTICAL
            content().forEach(this::addView)
        }
}

inline fun View.column(
    modifier: LinearLayout.() -> Unit = {},
    content: ViewGroup.() -> List<View> = { emptyList() },
): LinearLayout {
    return context.column(modifier, content)
}

inline fun Context.box(
    modifier: FrameLayout.() -> Unit = {},
    content: ViewGroup.() -> List<View> = { emptyList() },
): FrameLayout {
    return FrameLayout(this)
        .apply(modifier)
        .apply {
            content().forEach(this::addView)
        }
}

inline fun View.box(
    modifier: FrameLayout.() -> Unit = {},
    content: ViewGroup.() -> List<View> = { emptyList() },
): FrameLayout {
    return context.box(modifier, content)
}

inline fun Context.scrollableRow(
    modifier: HorizontalScrollView.() -> Unit = {},
    content: ViewGroup.() -> List<View> = { emptyList() },
): HorizontalScrollView {
    return HorizontalScrollView(this)
        .apply(modifier)
        .apply {
            content().forEach(this::addView)
        }
}

inline fun View.scrollableRow(
    modifier: HorizontalScrollView.() -> Unit = {},
    content: ViewGroup.() -> List<View> = { emptyList() },
): HorizontalScrollView {
    return context.scrollableRow(modifier, content)
}