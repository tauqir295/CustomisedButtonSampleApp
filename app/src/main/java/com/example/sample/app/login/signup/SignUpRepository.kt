package com.example.sample.app.login.signup

import androidx.annotation.WorkerThread
import com.example.sample.app.database.UserDao
import com.example.sample.app.database.model.User
import javax.inject.Inject

class SignUpRepository @Inject constructor(private val userDao: UserDao) {

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    fun insert(user: User) {
        userDao.insert(user)
    }

    @WorkerThread
    fun getUsers(): List<User>{
        return userDao.getAll()
    }
}