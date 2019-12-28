package com.osequeiros.thingscounter.data

import com.osequeiros.thingscounter.data.room.entity.ItemRoom
import com.osequeiros.thingscounter.domain.model.Item

class ItemDataMapper {

    fun map(item: Item): ItemRoom {
        return ItemRoom(
            localCode = item.code,
            name = item.name,
            quantity = item.quantity
        )
    }

    fun map(items: List<ItemRoom>): List<Item> {
        return items.map { map(it) }
    }

    private fun map(item: ItemRoom): Item {
        return Item(
            code = item.localCode,
            name = item.name,
            quantity = item.quantity
        )
    }
}