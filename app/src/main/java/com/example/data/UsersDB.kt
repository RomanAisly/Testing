package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UsersEntity::class], version = 1, exportSchema = false)
abstract class UsersDB: RoomDatabase() {
    abstract fun dao(): UserDao
}