package com.example.sample.app.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.sample.app.R
import com.example.sample.app.login.ui.LoginActivity
import com.example.sample.app.session.SessionManagerViewModel
import com.example.sample.app.session.SessionTimeOut
import com.example.sample.app.session.SessionUtils
import com.example.sample.app.session.SessionWorker.Companion.DEFAULT_INTERACTION_COUNTER
import com.example.sample.app.session.SessionWorker.Companion.SESSION_TIMEOUT_RESULT_KEY
import com.example.sample.app.session.SessionWorker.Companion.ZERO
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {

    private val viewModel: SessionManagerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startTrackingSession()
    }

    /**
     * start tracking session time out
     */
    private fun startTrackingSession() {
        viewModel.run {
            startTrackingSession()
            getOutputWorkInfo().observe(this@BaseActivity, {

                if (it.isNullOrEmpty()) return@observe

                val sessionTimeState = SessionTimeOut.values()[it[0].outputData.getInt(SESSION_TIMEOUT_RESULT_KEY, ZERO)]

                if (sessionTimeState == SessionTimeOut.REFRESH_REQUIRED) {
                    // reset the counter
                    SessionUtils.resetInteractionCounter()
                    // reset the worker
                    startTrackingSession()

                    // --- add api call here for keeping server session alive ---

                } else if (sessionTimeState == SessionTimeOut.TIMED_OUT) {
                    logout(true)
                }
            })
        }
    }

    /**
     * logout of the app
     */
    fun logout(hasSessionExpired: Boolean) {
        if (hasSessionExpired) {
            Toast.makeText(this, getString(R.string.session_expired), Toast.LENGTH_SHORT).show()
        }
        viewModel.cancelSessionTracking()
        startActivity(Intent(this, LoginActivity::class.java))
        this.finish()
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        // increase the session tracking counter
        SessionUtils.increaseInteractionCounter()
    }

    override fun onResume() {
        super.onResume()

        // this is used for session timeout check after app comes to foreground
        viewModel.apply {
            if (timeAppWasInBackground != DEFAULT_INTERACTION_COUNTER && System.currentTimeMillis() - timeAppWasInBackground > 10) {
                logout(true)
            } else {
                timeAppWasInBackground = DEFAULT_INTERACTION_COUNTER
            }
        }
    }

    override fun onPause() {
        super.onPause()
        // this is used for session timeout calculation when app goes in background
        viewModel.run {
            if (timeAppWasInBackground == DEFAULT_INTERACTION_COUNTER) {
                timeAppWasInBackground = System.currentTimeMillis()
            }
        }
    }
}