package com.google.android.exoplayer2.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import java.io.FileInputStream
import java.io.InputStream

const val LOG_TAG = "ExoplayerUI"
const val REPL_FORM_CLASS = "com.google.appinventor.components.runtime.ReplForm"

private val Context.debugMode: Boolean
    get() {
        return try {
            Class.forName(REPL_FORM_CLASS).isAssignableFrom(this::class.java)
        } catch (e: ClassNotFoundException) {
            false
        }
    }

fun Context.getIcon(fileName: String): Drawable? {
    try {
        getAssetStream(fileName).apply {
            val drawable = Drawable.createFromStream(this, null)
            close()
            return drawable
        }
    } catch (e: Exception) {
        Log.v(LOG_TAG, "Error ($e) occurred while loading drawable. DebugMode = $debugMode")
    }
    return null
}

fun Context.getAssetStream(file: String): InputStream {
    return when {
        debugMode -> {
            val path = getExternalFilesDir(null).toString() + "/assets/" + file
            FileInputStream(path)
        }
        else -> assets.open(file)
    }
}