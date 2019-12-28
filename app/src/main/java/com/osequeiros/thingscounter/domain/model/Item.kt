package com.osequeiros.thingscounter.domain.model

data class Item(
    val code: String,
    val title: String,
    val quantity: Int = 0
)