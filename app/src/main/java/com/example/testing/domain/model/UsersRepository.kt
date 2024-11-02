package com.example.testing.domain.model

import com.example.testing.data.remote.CheckConnection
import com.example.testing.data.remote.dto.Data
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getUsers(page: Int, forceFetch: Boolean): Flow<CheckConnection<List<Users>>>
}