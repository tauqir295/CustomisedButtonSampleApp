package com.example.sample.app.database

import androidx.room.*
import com.example.sample.app.database.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query(
        "SELECT * FROM user WHERE user_name LIKE :userName AND " +
                "password LIKE :password LIMIT 1"
    )
    suspend fun findByName(userName: String, password: String): User?

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Insert
    suspend fun insert(user: User)
}