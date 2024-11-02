package com.example.testing.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UsersEntity(
    val avatar: String,
    val email: String,
    val first_name: String,
    val last_name: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int
)
