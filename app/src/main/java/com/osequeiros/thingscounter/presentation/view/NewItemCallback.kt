package com.osequeiros.thingscounter.presentation.view

import com.osequeiros.thingscounter.presentation.model.UiItem

interface NewItemCallback {

    fun addItem(item: UiItem)
}