package com.osequeiros.thingscounter.data

import com.osequeiros.thingscounter.domain.model.Item

class ItemDataRepository(private val localDataSource: LocalDataSource,
                         private val remoteDataSource: RemoteDataSource,
                         private val mapper: ItemDataMapper) : ItemRepository {

    override fun save(item: Item) {
        return localDataSource.save(mapper.map(item))
    }

    override fun get(): List<Item> {
        return mapper.map(localDataSource.get())
    }
}