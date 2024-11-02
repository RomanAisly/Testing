package com.example.testing.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert
    suspend fun UpsertUsers(users: List<UsersEntity>)

    @Query("SELECT * FROM usersentity")
    fun getUsers(): List<UsersEntity>
}