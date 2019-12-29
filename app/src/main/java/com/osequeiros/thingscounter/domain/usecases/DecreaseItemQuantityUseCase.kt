package com.osequeiros.thingscounter.domain.usecases

import com.osequeiros.thingscounter.domain.exceptions.ForbiddenDecreaseException
import com.osequeiros.thingscounter.domain.model.Item
import io.reactivex.Single

class DecreaseItemQuantityUseCase {

    fun execute(item: Item): Single<Item> {
        return Single.fromCallable {
            validateNoNegativeValue(item)
            item.copy(quantity = item.quantity - 1)
        }
    }

    private fun validateNoNegativeValue(item: Item) {
        if (item.quantity == 0) {
            throw ForbiddenDecreaseException()
        }
    }
}