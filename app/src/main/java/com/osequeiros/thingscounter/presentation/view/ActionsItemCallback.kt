package com.osequeiros.thingscounter.presentation.view

import com.osequeiros.thingscounter.presentation.model.ItemModel

interface ActionsItemCallback {

    fun increase(item: ItemModel)

    fun decrease(item: ItemModel)
}