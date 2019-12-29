package com.osequeiros.thingscounter.presentation

import com.osequeiros.thingscounter.presentation.model.ItemModel

interface CounterContract {

    interface View {
        fun showEmptyList()
        fun showItemList(items: List<ItemModel>)
        fun prohibitDecrease()
        fun showTotal(message: String)
    }

    interface Presenter {
        fun getItems()
        fun createItem(itemModel: ItemModel)
        fun increaseItem(itemModel: ItemModel)
        fun decreaseItem(itemModel: ItemModel)
    }
}