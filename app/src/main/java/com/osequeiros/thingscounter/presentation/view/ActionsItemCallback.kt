package com.osequeiros.thingscounter.presentation.view

import com.osequeiros.thingscounter.presentation.model.UiItem

interface ActionsItemCallback {

    fun increase(item: UiItem)

    fun decrease(item: UiItem)

    fun delete(item: UiItem)
}