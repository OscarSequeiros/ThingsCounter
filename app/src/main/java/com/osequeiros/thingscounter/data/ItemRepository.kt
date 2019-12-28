package com.osequeiros.thingscounter.data

import com.osequeiros.thingscounter.domain.model.Item

interface ItemRepository {

    fun save(item: Item)

    fun get(): List<Item>
}