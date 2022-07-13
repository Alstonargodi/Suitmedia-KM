package com.example.suitmedia_km_test.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.suitmedia_km_test.data.local.entity.user.NameTaken
import com.example.suitmedia_km_test.data.local.entity.user.User

@Dao
interface LocalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserName(data : User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNameTaken(data : NameTaken)

    @Query("select*from UserTable")
    fun readUserName(): LiveData<User>

    @Query("select*from NamePickTable")
    fun readNameTaken(): LiveData<NameTaken>

}