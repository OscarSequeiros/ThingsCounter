package com.osequeiros.thingscounter.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Item")
data class ItemRoom(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_code")
    val localCode: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "quantity")
    val quantity: Int,
    @ColumnInfo(name = "was_send")
    val wasSend: Boolean = false

)