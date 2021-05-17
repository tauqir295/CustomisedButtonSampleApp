package com.example.sample.app.session

import com.example.sample.app.session.SessionWorker.Companion.DEFAULT_INTERACTION_COUNTER

internal object SessionUtils {

    var interactionCounter = DEFAULT_INTERACTION_COUNTER

    fun resetInteractionCounter() {
        interactionCounter = DEFAULT_INTERACTION_COUNTER
    }

    fun increaseInteractionCounter() {
        interactionCounter++
    }

}