package com.osequeiros.thingscounter.presentation

import com.osequeiros.thingscounter.mvibase.MviIntent
import com.osequeiros.thingscounter.presentation.model.UiItem

sealed class ItemsIntent : MviIntent {

    object LoadAllItemsIntent : ItemsIntent()

    data class NameItemIntent(val name: String) : ItemsIntent()

    data class CreateItemIntent(val item: UiItem) : ItemsIntent()

    data class IncreaseCountIntent(val item: UiItem) : ItemsIntent()

    data class DecreaseCountIntent(val item: UiItem) : ItemsIntent()

    data class DeleteItemIntent(val item: UiItem) : ItemsIntent()
}