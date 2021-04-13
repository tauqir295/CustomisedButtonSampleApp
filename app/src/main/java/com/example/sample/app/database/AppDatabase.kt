package com.example.sample.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sample.app.database.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}