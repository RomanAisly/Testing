package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UsersEntity(
    val avatar: String,
    val firstName: String,
    val lastName: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
