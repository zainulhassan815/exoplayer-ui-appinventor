package com.google.android.exoplayer2.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
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

fun Context.getIcon(fileNameRes: Int): Drawable? {
    try {
        getAssetStream(getString(fileNameRes)).apply {
            val drawable = Drawable.createFromStream(this, null)
            close()
            return drawable
        }
    } catch (e: Exception) {
        Log.v(LOG_TAG, "getDrawable : Error = $e")
    }
    return null
}

fun Context.getIcon(fileName: String): Drawable? {
    try {
        getAssetStream(fileName).apply {
            val drawable = Drawable.createFromStream(this, null)
            close()
            return drawable
        }
    } catch (e: Exception) {
        Log.v(LOG_TAG, "getDrawable : Error = $e")
    }
    return null
}

fun Context.getAssetStream(file: String): InputStream {
    val activityName = javaClass.simpleName
    return when {
        debugMode -> assets.open(file)
        activityName.contains("makeroid") -> {
            val path = if (Build.VERSION.SDK_INT >= 29) {
                getExternalFilesDir(null).toString() + "/assets/" + file
            } else {
                "/storage/emulated/0/Kodular/assets/$file"
            }
            FileInputStream(path)
        }
        activityName.contains("appinventor") -> {
            val path = if (Build.VERSION.SDK_INT >= 29) {
                getExternalFilesDir(null).toString() + "/assets/" + file
            } else {
                getExternalFilesDir(null).toString() + "/assets/" + file
            }
            FileInputStream(path)
        }
        else -> assets.open(file)
    }
}