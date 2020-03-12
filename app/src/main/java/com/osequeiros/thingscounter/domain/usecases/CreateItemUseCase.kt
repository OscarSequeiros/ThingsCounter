package com.osequeiros.thingscounter.domain.usecases

import com.osequeiros.thingscounter.data.ItemRepository
import com.osequeiros.thingscounter.domain.exceptions.ItemNameExpectedException
import com.osequeiros.thingscounter.domain.model.Item
import io.reactivex.Completable
import io.reactivex.Single

class CreateItemUseCase(private val repository: ItemRepository) {

    fun execute(item: Item): Single<Item> {
        return validateFields(item)
            .andThen(repository.create(item))

    }

    private fun validateFields(item: Item): Completable {
        return Completable.fromAction {
            if (item.name.isBlank()) {
                throw ItemNameExpectedException()
            }
        }
    }
}