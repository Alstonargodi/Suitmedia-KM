package com.example.suitmedia_km_test.data.remote.service

import com.example.suitmedia_km_test.data.remote.Users
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUser(
        @Query("page") page : Int,
        @Query("per_page") perpage : Int = 6
    ): List<Users>
}