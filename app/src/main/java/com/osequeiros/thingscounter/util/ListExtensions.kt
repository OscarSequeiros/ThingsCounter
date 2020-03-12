package com.osequeiros.thingscounter.util

import android.view.View

fun <T> List<T>.replaceAll(newElement: T, predicate: (T) -> Boolean): List<T> {
    return map { if (predicate(it)) newElement else it }
}

fun View.visible(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}