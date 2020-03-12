package com.osequeiros.thingscounter.presentation.model

import com.osequeiros.thingscounter.domain.model.Item

class UiItemMapper {

    fun map(itemModel: UiItem): Item {
        return Item(
            code = itemModel.code,
            name = itemModel.title,
            quantity = itemModel.quantity
        )
    }

    fun map(item: Item): UiItem {
        return UiItem(
            code = item.code,
            title = item.name,
            quantity = item.quantity
        )
    }

    fun map(items: List<Item>): List<UiItem> {
        return items.map { map(it) }
    }
}