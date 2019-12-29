package com.osequeiros.thingscounter.domain.model

data class Item(
    val code: Long,
    val name: String,
    val quantity: Int = 0
)