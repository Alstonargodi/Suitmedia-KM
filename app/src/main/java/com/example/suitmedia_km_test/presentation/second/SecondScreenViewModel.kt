package com.example.suitmedia_km_test.presentation.second

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.suitmedia_km_test.data.local.entity.user.NameTaken
import com.example.suitmedia_km_test.data.local.entity.user.User
import com.example.suitmedia_km_test.data.repository.MainRepository

class SecondScreenViewModel(private val repository: MainRepository): ViewModel() {
    fun readUserName(): LiveData<User> =
        repository.readUserName()

    fun readNameTaken():LiveData<NameTaken> =
        repository.readNameTaken()
}