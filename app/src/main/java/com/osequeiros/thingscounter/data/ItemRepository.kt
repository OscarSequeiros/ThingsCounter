package com.osequeiros.thingscounter.data

import com.osequeiros.thingscounter.domain.model.Item
import io.reactivex.Completable
import io.reactivex.Single

interface ItemRepository {

    fun save(item: Item): Completable

    fun get(): Single<List<Item>>

    fun delete(item: Item): Completable
}