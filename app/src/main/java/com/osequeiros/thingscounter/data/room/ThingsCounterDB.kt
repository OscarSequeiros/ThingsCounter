package com.osequeiros.thingscounter.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.osequeiros.thingscounter.data.room.dao.ItemDao
import com.osequeiros.thingscounter.data.room.entity.ItemRoom

@Database(
    entities = [ItemRoom::class],
    version = 1
)
abstract class ThingsCounterDB : RoomDatabase() {

    abstract fun itemRoomDao(): ItemDao

    companion object {

        private const val DATABASE_NAME = "thingscounter.db"
        @Volatile
        private var instance: ThingsCounterDB? = null

        private fun buildDatabase(context: Context) = Room
            .databaseBuilder(context, ThingsCounterDB::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

        fun getInstance(context: Context): ThingsCounterDB {
            if (instance == null) {
                synchronized(ThingsCounterDB::class) { instance = buildDatabase(context) }
            }
            return instance!!
        }
    }
}