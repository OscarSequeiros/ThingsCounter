package com.osequeiros.thingscounter.data

import com.osequeiros.thingscounter.domain.model.Item
import io.reactivex.Completable
import io.reactivex.Single

class ItemDataRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val mapper: ItemDataMapper
) : ItemRepository {

    override fun save(item: Item): Completable {
        return localDataSource.save(mapper.map(item))
    }

    override fun get(): Single<List<Item>> {
        return localDataSource.get()
            .map { mapper.map(it) }
    }
}