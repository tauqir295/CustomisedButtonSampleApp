package com.example.sample.app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sample.app.database.model.User

@Dao
interface UserDao {
    @Query(
        "SELECT full_name FROM user WHERE user_name LIKE :userName AND " +
                "password LIKE :password"
    )
    suspend fun findByUserNameAndPassword(userName: String, password: String): String?

    @Insert
    suspend fun insert(user: User)
}