package com.osequeiros.thingscounter.presentation

import com.osequeiros.thingscounter.presentation.model.ItemModel
import com.osequeiros.thingscounter.rx.BasePresenter

interface CounterContract {

    interface View {
        fun showEmptyState()
        fun hideEmptyState()
        fun showItemList(items: List<ItemModel>)
        fun prohibitDecrease()
        fun showNameRequiredMessage()
        fun showTotal(message: String)
    }

    interface Presenter : BasePresenter {
        fun getItems()
        fun createItem(itemModel: ItemModel)
        fun increaseItem(itemModel: ItemModel)
        fun decreaseItem(itemModel: ItemModel)
        fun deleteItem(itemModel: ItemModel)
    }
}