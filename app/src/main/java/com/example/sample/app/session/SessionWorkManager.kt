package com.example.sample.app.session

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class SessionWorkManager(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        var timeLeftInSession = SESSION_TIME_OUT

        while (timeLeftInSession > 0 && !isStopped) {
            sleep()

            timeLeftInSession -= SESSION_TIME_OUT_CHECK_DELAY
        }
        return Result.success()
    }

    /**
     * Method for sleep for 1 sec
     */
    private fun sleep() {
        try {
            Thread.sleep(SESSION_TIME_OUT_CHECK_DELAY, 0)
        } catch (e: InterruptedException) {
            println(e.printStackTrace())
        }
    }

    companion object {
        const val SESSION_TIME_OUT = 30 * 1000L
        const val SESSION_TIME_OUT_CHECK_DELAY = 1000L
    }
}