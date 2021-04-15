package com.example.sample.app.home

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mobile.design.lib.CustomizableGenericButton
import com.example.sample.app.R
import com.example.sample.app.databinding.ActivityMainBinding
import com.example.sample.app.utils.LOGGED_IN_USER

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)

        binding.run {
            binding.fullName = intent.getStringExtra(LOGGED_IN_USER)
        }

        findViewById<CustomizableGenericButton>(R.id.logoutBtn).run {
            setOnClickListener {
                logout(false)
            }
        }
    }
}