package com.google.android.exoplayer2.ui

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout

inline val View.matchParent
    get() = ViewGroup.LayoutParams.MATCH_PARENT

inline val View.wrapContent
    get() = ViewGroup.LayoutParams.WRAP_CONTENT

@PublishedApi
internal const val NO_GETTER = "Property does not have a getter"

@PublishedApi
internal inline val noGetter: Nothing
    get() = throw UnsupportedOperationException(NO_GETTER)

val Int.dp: Int
    get() = Resources.getSystem().displayMetrics.density.times(this).toInt()

val Float.dp: Int
    get() = Resources.getSystem().displayMetrics.density.times(this).toInt()

val Double.dp: Int
    get() = Resources.getSystem().displayMetrics.density.times(this.toFloat()).toInt()

fun View.padding(padding: Int) {
    setPadding(padding, padding, padding, padding)
}

fun View.padding(horizontal: Int, vertical: Int) {
    setPadding(horizontal, vertical, horizontal, vertical)
}

inline fun View.frameLayoutParams(
    width: Int = wrapContent,
    height: Int = wrapContent,
    @SuppressLint("InlinedApi")
    gravity: Int = FrameLayout.LayoutParams.UNSPECIFIED_GRAVITY,
    initParams: FrameLayout.LayoutParams.() -> Unit = {}
): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(width, height).also {
        it.gravity = gravity
    }.apply(initParams)
}

inline fun View.linearLayoutParams(
    width: Int = wrapContent,
    height: Int = wrapContent,
    initParams: LinearLayout.LayoutParams.() -> Unit = {}
): LinearLayout.LayoutParams {
    return LinearLayout.LayoutParams(width, height).apply(initParams)
}

inline fun View.linearLayoutParams(
    width: Int = wrapContent,
    height: Int = wrapContent,
    gravity: Int = -1,
    weight: Float = 0f,
    initParams: LinearLayout.LayoutParams.() -> Unit = {}
): LinearLayout.LayoutParams {
    return LinearLayout.LayoutParams(width, height, weight).also {
        it.gravity = gravity
    }.apply(initParams)
}

inline var ViewGroup.MarginLayoutParams.horizontalMargin: Int
    get() = noGetter
    set(value) {
        leftMargin = value
        rightMargin = value
    }

inline var ViewGroup.MarginLayoutParams.verticalMargin: Int
    get() = noGetter
    set(value) {
        topMargin = value
        bottomMargin = value
    }

inline var ViewGroup.MarginLayoutParams.margin: Int
    get() = noGetter
    set(value) {
        leftMargin = value
        topMargin = value
        rightMargin = value
        bottomMargin = value
    }

inline var ViewGroup.MarginLayoutParams.startMargin: Int
    get() = marginStart
    set(value) {
        marginStart = value
    }

inline var ViewGroup.MarginLayoutParams.endMargin: Int
    get() = marginEnd
    set(value) {
        marginEnd = value
    }