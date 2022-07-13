package com.example.suitmedia_km_test.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.suitmedia_km_test.data.local.dao.LocalDao
import com.example.suitmedia_km_test.data.local.dao.RemoteDao
import com.example.suitmedia_km_test.data.local.entity.mediator.RemoteKeys
import com.example.suitmedia_km_test.data.local.entity.user.NameTaken
import com.example.suitmedia_km_test.data.local.entity.user.User
import com.example.suitmedia_km_test.data.remote.Data

@Database(entities = [
    User::class,
    NameTaken::class,
    Data::class,
    RemoteKeys::class
], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun localDao() : LocalDao
    abstract fun remoteDao(): RemoteDao

    companion object{
        @Volatile
        private var instance : LocalDatabase? = null

        fun setDatabase(context: Context): LocalDatabase{
            return instance ?: synchronized(this){
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,"MainDB"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }

    }
}