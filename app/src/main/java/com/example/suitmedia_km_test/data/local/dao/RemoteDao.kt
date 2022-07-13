package com.example.suitmedia_km_test.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.suitmedia_km_test.data.local.entity.mediator.RemoteKeys
import com.example.suitmedia_km_test.data.remote.Data

@Dao
interface RemoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKeys(keys : List<RemoteKeys>)

    @Query("select * from remoteKeys where id = :id")
    suspend fun getRemoteKeysId(id : Int): RemoteKeys?

    @Query("delete from remoteKeys")
    suspend fun deleteRemoteKeys()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsersList(data : List<Data>)

    @Query("select * from UsersTable")
    fun readUsersList(): PagingSource<Int,Data>

    @Query("delete from UsersTable")
    suspend fun deleteUsersList()

}