package com.example.suitmedia_km_test.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.suitmedia_km_test.data.local.database.LocalDatabase
import com.example.suitmedia_km_test.data.local.entity.user.NameTaken
import com.example.suitmedia_km_test.data.local.entity.user.User
import com.example.suitmedia_km_test.data.remote.Data
import com.example.suitmedia_km_test.data.remote.service.ApiService
import com.example.suitmedia_km_test.helpers.paging.RemotePaging
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


    fun getUsersList(): LiveData<PagingData<Data>>{
        return Pager(
            config = PagingConfig(1),
            pagingSourceFactory = { RemotePaging(apiService) }
        ).liveData
    }


}