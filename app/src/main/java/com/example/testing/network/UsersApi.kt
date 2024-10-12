package com.example.testing.network

import com.example.testing.network.dto.UsersDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {
    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int
    ):UsersDTO
}

