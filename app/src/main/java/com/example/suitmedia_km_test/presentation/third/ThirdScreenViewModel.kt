package com.example.suitmedia_km_test.presentation.third

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.suitmedia_km_test.data.local.entity.user.NameTaken
import com.example.suitmedia_km_test.data.remote.Data
import com.example.suitmedia_km_test.data.repository.MainRepository

class ThirdScreenViewModel(private val repository: MainRepository): ViewModel() {
    fun getUsersList(): LiveData<PagingData<Data>> =
        repository.getUsersList().cachedIn(viewModelScope)

    fun saveNameTaken(data : NameTaken){
        repository.insertNameTaken(data)
    }
}