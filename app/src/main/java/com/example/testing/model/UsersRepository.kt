package com.example.testing.model

import com.example.testing.network.CheckConnection
import com.example.testing.network.dto.UsersDTO
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getUsers(): Flow<CheckConnection<List<UsersDTO>>>

}