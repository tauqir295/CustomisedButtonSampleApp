package com.example.sample.app.session

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.sample.app.session.SessionWorkManager.Companion.DEFAULT_INTERACTION_COUNTER
import com.example.sample.app.session.SessionWorkManager.Companion.ID_SESSION_TIMEOUT_WORK
import com.example.sample.app.session.SessionWorkManager.Companion.SESSION_TIMEOUT_TAG
import javax.inject.Inject

class SessionManagerViewModel @Inject constructor(): ViewModel() {

    private val workManager = WorkManager.getInstance()

    var timeAppWasInBackground = DEFAULT_INTERACTION_COUNTER

    fun startTrackingSession() {
        val save = OneTimeWorkRequest.Builder(SessionWorkManager::class.java)
            .addTag(SESSION_TIMEOUT_TAG)
            .build()

        workManager.beginUniqueWork(ID_SESSION_TIMEOUT_WORK, ExistingWorkPolicy.REPLACE, save).enqueue()
    }

    fun cancelSessionTracking() {
        workManager.cancelUniqueWork(ID_SESSION_TIMEOUT_WORK)
    }

    // this transformation ensures whenever current work id changes work info,
    // the UI will be observing the changes
    fun getOutputWorkInfo(): LiveData<List<WorkInfo>> {
        return workManager.getWorkInfosByTagLiveData(SESSION_TIMEOUT_TAG)
    }
}