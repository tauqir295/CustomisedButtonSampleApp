package com.example.sample.app.login.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sample.app.R
import com.example.sample.app.login.signup.SignUpFragment
import com.example.sample.app.utils.HAS_USER_SIGNED_UP
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Login activity manages sign up and login fragment
 */
@AndroidEntryPoint
class LoginActivity: AppCompatActivity(R.layout.activity_login)  {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val hasUserSignedUp = sharedPreferences.getBoolean(HAS_USER_SIGNED_UP, false)
        var fragment: Fragment = SignUpFragment.newInstance()
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