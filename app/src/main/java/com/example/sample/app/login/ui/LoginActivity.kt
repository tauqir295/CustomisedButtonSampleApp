package com.example.sample.app.login.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sample.app.R
import com.example.sample.app.login.signup.SignUpFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: AppCompatActivity(R.layout.activity_login)  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.container,
                SignUpFragment.newInstance()
            ).commitNow()
        }
    }
}