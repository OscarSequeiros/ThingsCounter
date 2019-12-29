package com.osequeiros.thingscounter.presentation.model

import com.osequeiros.thingscounter.domain.model.Item

class ItemModelMapper {

    fun map(itemModel: ItemModel): Item {
        return Item(
            code = itemModel.code,
            name = itemModel.title,
            quantity = itemModel.quantity
        )
    }

    fun map(item: Item): ItemModel {
        return ItemModel(
            code = item.code,
            title = item.name,
            quantity = item.quantity
        )
    }

    fun map(items: List<Item>): List<ItemModel> {
        return items.map { map(it) }
    }
}