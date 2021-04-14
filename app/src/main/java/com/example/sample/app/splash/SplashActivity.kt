package com.example.sample.app.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sample.app.R
import com.example.sample.app.database.AppDatabase
import com.example.sample.app.database.model.User
import com.example.sample.app.login.ui.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Activity for entry point in app.
 */
@AndroidEntryPoint
class SplashActivity: AppCompatActivity(R.layout.activity_splash)  {

    @Inject
    lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, LoginActivity::class.java)
        GlobalScope.launch {
            val userDao = appDatabase.userDao()
            val users: List<User> = userDao.getAll()
            intent.putExtra("hasUserSignedUp", users.isNotEmpty())
            delay(1000)
            startActivity(intent)
            finish()
        }
    }
}