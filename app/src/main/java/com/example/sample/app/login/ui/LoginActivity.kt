package com.example.sample.app.login.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sample.app.R
import com.example.sample.app.login.signup.SignUpFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: AppCompatActivity(R.layout.activity_login)  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var fragment: Fragment = SignUpFragment.newInstance()
        val hasUserSignedUp = intent.getBooleanExtra("hasUserSignedUp", false)
        if (savedInstanceState == null) {
            if (hasUserSignedUp) {
                fragment = LoginFragment.newInstance()
            }
            supportFragmentManager.beginTransaction().replace(
                R.id.container,
                fragment
            ).commitNow()
        }
    }
}