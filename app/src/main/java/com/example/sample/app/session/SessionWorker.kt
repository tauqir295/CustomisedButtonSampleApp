package com.example.sample.app.session

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class SessionWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        var timeLeftInSession = SESSION_TIME_OUT

        while (timeLeftInSession > ZERO && !isStopped) {
            sleep()
            timeLeftInSession -= SESSION_TIME_OUT_CHECK_DELAY

            // reset the session timeout in case user is interacting with the app.
            if (SessionUtils.interactionCounter > ZERO) {

                // call the api which will be used for keeping server session alive
                // use below result to do so
                val outputData = Data.Builder()
                        .putInt(SESSION_TIMEOUT_RESULT_KEY, SessionTimeOut.REFRESH_REQUIRED.ordinal)
                        .build()
                return Result.success(outputData)
            }
        }

        // take the action based on session time out. Use the result data accordingly
        return if (timeLeftInSession <= ZERO) {
            val outputData = Data.Builder()
                .putInt(SESSION_TIMEOUT_RESULT_KEY, SessionTimeOut.TIMED_OUT.ordinal)
                .build()
            Result.success(outputData)
        } else {
            // take the action based cancelled or failure
            val status = if (isStopped) SessionTimeOut.CANCELLED.ordinal else SessionTimeOut.FAILED.ordinal
            val outputData = Data.Builder()
                .putInt(SESSION_TIMEOUT_RESULT_KEY, status)
                .build()
            Result.failure(outputData)
        }
    }

    /**
     * Method for sleep for 1 sec
     */
    private fun sleep() {
        try {
            Thread.sleep(SESSION_TIME_OUT_CHECK_DELAY, ZERO)
        } catch (e: InterruptedException) {
            println(e.printStackTrace())
        }
    }

    companion object {
        const val SESSION_TIME_OUT = 30 * 1000L
        const val SESSION_TIME_OUT_CHECK_DELAY = 1000L
        const val SESSION_TIMEOUT_TAG = "session_timeout_tag"
        const val SESSION_TIMEOUT_RESULT_KEY = "session_timeout_result_key"
        const val ID_SESSION_TIMEOUT_WORK = "id_session_timeout_work"
        const val DEFAULT_INTERACTION_COUNTER = 0L
        const val ZERO = 0
    }
}