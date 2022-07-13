package com.example.suitmedia_km_test.data.local.entity.mediator

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remoteKeys")
data class RemoteKeys(
    @PrimaryKey
    val id : String,
    val prevKey : Int?,
    val nextKey : Int?
)