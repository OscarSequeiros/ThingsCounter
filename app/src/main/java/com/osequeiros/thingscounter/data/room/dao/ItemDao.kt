package com.osequeiros.thingscounter.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.osequeiros.thingscounter.data.room.entity.ItemRoom

@Dao
interface ItemDao {

    @Query("SELECT * FROM ITEM WHERE is_active = 1")
    fun get(): List<ItemRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ItemRoom)

    @Query("UPDATE ITEM SET is_active = 0 WHERE local_code = :itemCode")
    fun delete(itemCode: Long)

    @Query("SELECT * FROM ITEM WHERE was_send = 0")
    fun getPendingToSending(): List<ItemRoom>
}