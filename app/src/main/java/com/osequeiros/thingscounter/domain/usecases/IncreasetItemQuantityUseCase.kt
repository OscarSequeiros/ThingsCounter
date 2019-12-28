package com.osequeiros.thingscounter.domain.usecases

import com.osequeiros.thingscounter.domain.model.Item

class IncreasetItemQuantityUseCase {

    fun execute(item: Item): Item {
        return item.copy(quantity = item.quantity + 1)
    }
}