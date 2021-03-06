package com.example.sample.app.database

import androidx.annotation.WorkerThread
import com.example.sample.app.database.model.User
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val userDao: UserDao) {

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    @WorkerThread
    suspend fun getUser(userName: String, password: String): String? {
        return userDao.findByUserNameAndPassword(userName, password)
    }
}