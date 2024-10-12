package com.example.testing.model

import com.example.testing.network.CheckConnection
import com.example.testing.network.dto.Data
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getUsers(): Flow<CheckConnection<List<Data>>>

}