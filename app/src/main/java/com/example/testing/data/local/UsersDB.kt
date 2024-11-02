package com.example.testing.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UsersEntity::class], version = 1, exportSchema = false)
abstract class UsersDB: RoomDatabase() {
    abstract val dao: UserDao
}