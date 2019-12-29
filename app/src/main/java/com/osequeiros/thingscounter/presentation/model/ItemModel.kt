package com.osequeiros.thingscounter.presentation.model

data class ItemModel(
    val code: Long = 0,
    val title: String,
    val quantity: Int = 0
) {

    val subtitle: String
        get() = if (quantity == 1) {
            "$quantity item"
        } else {
            "$quantity items"
        }
}