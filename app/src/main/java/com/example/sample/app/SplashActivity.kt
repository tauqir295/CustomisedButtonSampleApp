package com.example.sample.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sample.app.login.ui.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Activity for entry point in app.
 */
@AndroidEntryPoint
class SplashActivity: AppCompatActivity(R.layout.activity_splash)  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, LoginActivity::class.java)
        GlobalScope.launch {
            delay(0)
            startActivity(intent)
            finish()
        }
    }
}