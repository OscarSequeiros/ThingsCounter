package com.osequeiros.thingscounter.presentation.view

import com.osequeiros.thingscounter.presentation.model.ItemModel

interface NewItemCallback {

    fun addItem(model: ItemModel)
}