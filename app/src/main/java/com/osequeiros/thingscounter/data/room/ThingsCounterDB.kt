package com.osequeiros.thingscounter.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.osequeiros.thingscounter.data.room.dao.ItemDao
import com.osequeiros.thingscounter.data.room.entity.ItemRoom

@Database(
    entities = [ItemRoom::class],
    version = 1
)
abstract class ThingsCounterDB : RoomDatabase() {

    abstract fun itemRoomDao(): ItemDao
}