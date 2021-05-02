package com.example.sample.app

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sample.app.database.AppDatabase
import com.example.sample.app.database.UserDao
import com.example.sample.app.database.model.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndRead() {
        GlobalScope.launch {
            val user = User(0, "name", "full name", "password")
            userDao.insert(user)

            val data = userDao.findByUserNameAndPassword("name", "password")
            Assert.assertEquals(data, "full name")
        }
    }
}