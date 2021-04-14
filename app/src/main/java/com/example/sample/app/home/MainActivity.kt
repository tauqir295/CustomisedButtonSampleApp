package com.example.sample.app.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile.design.lib.CustomizableGenericButton
import com.example.sample.app.R
import com.example.sample.app.splash.SplashActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<CustomizableGenericButton>(R.id.logoutBtn).run {
            setOnClickListener {
                startActivity(Intent(this@MainActivity, SplashActivity::class.java))
                this@MainActivity.finish()
            }
        }
    }
}