package com.osequeiros.thingscounter.domain.usecases

import com.osequeiros.thingscounter.domain.model.Item
import io.reactivex.Single

class IncreaseItemQuantityUseCase() {

    fun execute(item: Item): Single<Item> {
        return Single.fromCallable { item.copy(quantity = item.quantity + 1) }
    }
}