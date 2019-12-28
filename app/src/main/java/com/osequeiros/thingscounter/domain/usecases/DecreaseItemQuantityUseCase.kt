package com.osequeiros.thingscounter.domain.usecases

import com.osequeiros.thingscounter.domain.model.Item

class DecreaseItemQuantityUseCase {

    fun execute(item: Item): Item {
        return if (item.quantity > 0) {
            item.copy(quantity = item.quantity - 1)
        } else {
            item
        }
    }
}