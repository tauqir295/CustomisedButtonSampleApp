package com.example.sample.app.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.sample.app.session.SessionManagerViewModel
import com.example.sample.app.session.SessionTimeOut
import com.example.sample.app.session.SessionUtils
import com.example.sample.app.session.SessionWorkManager.Companion.SESSION_TIMEOUT_RESULT_KEY
import com.example.sample.app.splash.SplashActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseActivity: AppCompatActivity() {

    val viewModel: SessionManagerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startTrackingSession()
    }

    private fun startTrackingSession() {
        viewModel.run {
            startTrackingSession()
            getOutputWorkInfo().observe(this@BaseActivity, {

                if (it.isNullOrEmpty()) return@observe

                val sessionTimeState = SessionTimeOut.values()[it[0].outputData.getInt(SESSION_TIMEOUT_RESULT_KEY, 0)]

                if (sessionTimeState == SessionTimeOut.TIMED_OUT) {
                    SessionUtils.resetInteractionCounter()
                    logout()
                }
            })
        }
    }

    fun logout() {
        viewModel.cancelSessionTracking()
        startActivity(Intent(this, SplashActivity::class.java))
        this.finish()
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        SessionUtils.increaseInteractionCounter()
    }
}