package com.example.suitmedia_km_test.injection

import android.content.Context
import com.example.suitmedia_km_test.data.local.database.LocalDatabase
import com.example.suitmedia_km_test.data.remote.config.ApiConfig
import com.example.suitmedia_km_test.data.repository.MainRepository

object Injection {
    fun provideRepository(context: Context): MainRepository{
        return MainRepository(
            LocalDatabase.setDatabase(context),
            ApiConfig.getApiService()
        )
    }
}