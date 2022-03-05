package com.google.android.exoplayer2.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import java.io.FileInputStream
import java.io.InputStream

const val LOG_TAG = "ExoplayerUI"

fun Context.getIcon(fileNameRes: Int, debugMode: Boolean): Drawable? {
    try {
        getAssetStream(getString(fileNameRes), debugMode).apply {
            val drawable = Drawable.createFromStream(this, null)
            close()
            return drawable
        }
    } catch (e: Exception) {
        Log.v(LOG_TAG, "getDrawable : Error = $e")
    }
    return null
}

fun Context.getAssetStream(file: String, debugMode: Boolean): InputStream {
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
                getExternalFilesDir(null).toString() + "/AppInventor/assets/" + file
            }
            FileInputStream(path)
        }
        else -> assets.open(file)
    }
}