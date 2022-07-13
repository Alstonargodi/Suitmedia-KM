package com.example.suitmedia_km_test.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.suitmedia_km_test.data.repository.MainRepository
import com.example.suitmedia_km_test.injection.Injection
import com.example.suitmedia_km_test.presentation.first.FirstScreenViewModel
import com.example.suitmedia_km_test.presentation.second.SecondScreenViewModel
import com.example.suitmedia_km_test.presentation.third.ThirdScreenViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(
    private val repository: MainRepository
): ViewModelProvider.Factory{
    companion object{
        @Volatile
        private var instance : ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory{
            if (instance == null){
                synchronized(ViewModelFactory::class.java){
                    instance = ViewModelFactory(
                        Injection.provideRepository(context)
                    )
                }
            }
            return instance as ViewModelFactory
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if (modelClass.isAssignableFrom(FirstScreenViewModel::class.java)){
           return FirstScreenViewModel(repository) as T
       }else if (modelClass.isAssignableFrom(SecondScreenViewModel::class.java)){
           return SecondScreenViewModel(repository) as T
       }else if (modelClass.isAssignableFrom(ThirdScreenViewModel::class.java)) {
           return ThirdScreenViewModel(repository) as T
       }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

}