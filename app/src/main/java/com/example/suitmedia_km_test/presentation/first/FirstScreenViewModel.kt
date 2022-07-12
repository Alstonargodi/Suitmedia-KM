package com.example.suitmedia_km_test.presentation.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.suitmedia_km_test.data.local.entity.user.User
import com.example.suitmedia_km_test.data.remote.UsersResponse
import com.example.suitmedia_km_test.data.repository.MainRepository

class FirstScreenViewModel(private val repository: MainRepository): ViewModel() {
    fun insertUserName(data : User){
        repository.insertUsername(data)
    }


}