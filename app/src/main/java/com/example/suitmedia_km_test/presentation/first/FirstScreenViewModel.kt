package com.example.suitmedia_km_test.presentation.first

import androidx.lifecycle.ViewModel
import com.example.suitmedia_km_test.data.local.entity.user.User
import com.example.suitmedia_km_test.data.repository.MainRepository

class FirstScreenViewModel(private val repository: MainRepository): ViewModel() {
    fun insertUserName(data : User){
        repository.insertUsername(data)
    }


}