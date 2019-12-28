package com.osequeiros.thingscounter.data

import com.osequeiros.thingscounter.data.room.dao.ItemDao
import com.osequeiros.thingscounter.data.room.entity.ItemRoom

class LocalDataSource(private val dao: ItemDao) {

    fun save(itemRoom: ItemRoom) {
        return dao.insert(itemRoom)
    }

    fun get(): List<ItemRoom> {
        return dao.get()
    }
}