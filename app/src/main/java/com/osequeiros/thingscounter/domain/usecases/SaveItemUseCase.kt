package com.osequeiros.thingscounter.domain.usecases

import com.osequeiros.thingscounter.data.ItemRepository
import com.osequeiros.thingscounter.domain.exceptions.ItemNameExpectedException
import com.osequeiros.thingscounter.domain.model.Item
import io.reactivex.Completable

class SaveItemUseCase(private val repository: ItemRepository) {

    fun execute(item: Item): Completable = repository.save(item)
}