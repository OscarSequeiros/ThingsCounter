package com.osequeiros.thingscounter.data

import com.osequeiros.thingscounter.data.room.dao.RoomItem
import com.osequeiros.thingscounter.data.room.entity.ItemRoom
import io.reactivex.Completable
import io.reactivex.Single

class LocalDataSource(private val dao: RoomItem) {

    fun create(itemRoom: ItemRoom): Single<ItemRoom> {
        return Single.fromCallable { itemRoom.copy(localCode = dao.insert(itemRoom)) }
    }

    fun save(itemRoom: ItemRoom): Completable {
        return Completable.fromAction { dao.insert(itemRoom) }
    }

    fun get(): Single<List<ItemRoom>> {
        return Single.fromCallable { dao.get() }
    }

    fun delete(itemCode: Long): Completable {
        return Completable.fromAction { dao.delete(itemCode) }
    }

    fun getPending(): Single<List<ItemRoom>> {
        return Single.fromCallable { dao.getPendingToSending() }
    }
}