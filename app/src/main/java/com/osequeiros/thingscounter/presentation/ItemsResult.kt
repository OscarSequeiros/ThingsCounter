package com.osequeiros.thingscounter.presentation

import com.osequeiros.thingscounter.domain.model.Item
import com.osequeiros.thingscounter.mvibase.MviResult

sealed class ItemsResult : MviResult {

    sealed class LoadAllItemsResult : ItemsResult() {
        object Processing : LoadAllItemsResult()
        data class Success(val items: List<Item>) : LoadAllItemsResult()
        data class Failure(val error: Throwable) : LoadAllItemsResult()
    }

    sealed class NameItemResult : ItemsResult() {
        object Processing : NameItemResult()
        data class Success(val name: String) : NameItemResult()
        data class Failure(val error: Throwable) : NameItemResult()
    }

    sealed class CreateItemResult : ItemsResult() {
        object Processing : CreateItemResult()
        data class Success(val item: Item) : CreateItemResult()
        data class Failure(val error: Throwable) : CreateItemResult()
    }

    sealed class IncreaseCountResult : ItemsResult() {
        object Processing : IncreaseCountResult()
        data class Success(val item: Item) : IncreaseCountResult()
        data class Failure(val error: Throwable) : IncreaseCountResult()
    }

    sealed class DecreaseCountResult : ItemsResult() {
        object Processing : DecreaseCountResult()
        data class Success(val item: Item) : DecreaseCountResult()
        data class Failure(val error: Throwable) : DecreaseCountResult()
    }

    sealed class DeleteItemResult : ItemsResult() {
        object Processing : DeleteItemResult()
        data class Success(val item: Item) : DeleteItemResult()
        data class Failure(val error: Throwable) : DeleteItemResult()
    }
}