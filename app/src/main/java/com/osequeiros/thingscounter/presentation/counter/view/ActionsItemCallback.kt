package com.osequeiros.thingscounter.presentation.counter.view

import com.osequeiros.thingscounter.presentation.counter.model.ItemModel

interface ActionsItemCallback {

    fun increase(item: ItemModel)

    fun decrease(item: ItemModel)
}