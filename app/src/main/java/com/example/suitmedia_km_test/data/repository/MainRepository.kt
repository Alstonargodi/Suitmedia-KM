package com.example.suitmedia_km_test.data.repository

import androidx.lifecycle.LiveData
import com.example.suitmedia_km_test.data.local.database.LocalDatabase
import com.example.suitmedia_km_test.data.local.entity.user.NameTaken
import com.example.suitmedia_km_test.data.local.entity.user.User
import com.example.suitmedia_km_test.data.remote.service.ApiService
import java.util.concurrent.Executors

class MainRepository(
    localDatabase: LocalDatabase,
    private val apiService: ApiService
) {
    private val userDao = localDatabase.localDao()
    private val executorService = Executors.newSingleThreadExecutor()

    fun readUserName(): LiveData<User> = userDao.readUserName()
    fun readNameTaken(): LiveData<NameTaken> = userDao.readNameTaken()

    fun insertUsername(data : User){
        executorService.execute { userDao.insertUserName(data) }
    }

    fun insertNameTaken(data : NameTaken){
        executorService.execute { userDao.insertNameTaken(data) }
    }

}