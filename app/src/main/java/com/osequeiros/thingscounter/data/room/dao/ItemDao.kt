package com.osequeiros.thingscounter.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.osequeiros.thingscounter.data.room.entity.ItemRoom

@Dao
interface ItemDao {

    @Query("SELECT * FROM ITEM")
    fun get(): List<ItemRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ItemRoom)
}