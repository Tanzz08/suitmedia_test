package com.example.suitmediatest.data.remote

import com.example.suitmediatest.data.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Header("x-api-key") apiKey: String = "reqres-free-v1",
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): UserResponse
}