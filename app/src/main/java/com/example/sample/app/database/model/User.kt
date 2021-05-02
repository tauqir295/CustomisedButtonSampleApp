package com.example.sample.app.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "user_name") val userName: String?,
    @ColumnInfo(name = "full_name") val fullName: String?,
    @ColumnInfo(name = "password") val password: String?
)
