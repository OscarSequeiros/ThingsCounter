package com.osequeiros.thingscounter.domain.model

data class Item(
    val code: String,
    val name: String,
    val quantity: Int = 0
)