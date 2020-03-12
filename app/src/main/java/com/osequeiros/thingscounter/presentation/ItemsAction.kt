package com.osequeiros.thingscounter.presentation

import com.osequeiros.thingscounter.domain.model.Item
import com.osequeiros.thingscounter.mvibase.MviAction

sealed class ItemsAction : MviAction {

    object LoadAllItemsAction : ItemsAction()

    class NameItemAction(val name: String) : ItemsAction()

    class CreateItemAction(val item: Item) : ItemsAction()

    data class IncreaseCountAction(val item: Item) : ItemsAction()

    data class DecreaseCountAction(val item: Item) : ItemsAction()

    data class DeleteItemAction(val item: Item) : ItemsAction()
}