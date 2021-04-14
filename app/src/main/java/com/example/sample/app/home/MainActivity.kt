package com.example.sample.app.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mobile.design.lib.CustomizableGenericButton
import com.example.sample.app.R
import com.example.sample.app.login.ui.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<CustomizableGenericButton>(R.id.button4).apply {
            titleText = "Continue"
            subtitleText = "done via code"
            iconDrawable = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_settings)
            subtitleTvVisibility = true
        }

        findViewById<CustomizableGenericButton>(R.id.button5).apply {
            titleText = "This is an example for long title"
            subtitleText = "This is an example sub title"
            iconDrawable = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_settings)
            iconVisibility = true
            subtitleTvVisibility = true
        }

        findViewById<CustomizableGenericButton>(R.id.logoutBtn).run {
            setOnClickListener {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                this@MainActivity.finish()
            }
        }
    }
}