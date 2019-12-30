@file:Suppress("DEPRECATION")

package com.osequeiros.thingscounter.presentation

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast

fun Context.vibrate(timeInMillis: Long) {
    val vibrator: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator.vibrate(
            VibrationEffect.createOneShot(
                timeInMillis,
                VibrationEffect.DEFAULT_AMPLITUDE
            )
        )
    } else {
        vibrator.vibrate(timeInMillis)
    }
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}