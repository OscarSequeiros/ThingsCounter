package com.osequeiros.thingscounter.presentation.counter.view

import com.osequeiros.thingscounter.presentation.counter.model.ItemModel

interface NewItemCallback {

    fun addItem(model: ItemModel)
}