package com.example.suitmedia_km_test.data.local.entity.user

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "UserTable")
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = false)
    val id : Int,
    val name : String,
): Parcelable