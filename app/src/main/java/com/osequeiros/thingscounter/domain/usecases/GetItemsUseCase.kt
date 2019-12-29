package com.osequeiros.thingscounter.domain.usecases

import com.osequeiros.thingscounter.data.ItemRepository
import com.osequeiros.thingscounter.domain.model.Item
import io.reactivex.Single

class GetItemsUseCase(private val repository: ItemRepository) {

    fun execute(): Single<List<Item>> {
        return repository.get()
    }
}