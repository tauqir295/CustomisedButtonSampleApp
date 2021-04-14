package com.example.sample.app.session

import com.example.sample.app.session.SessionWorkManager.Companion.DEFAULT_INTERACTION_COUNTER

internal object SessionUtils {

    var interactionCounter = 0L

    fun resetInteractionCounter() {
        interactionCounter = DEFAULT_INTERACTION_COUNTER
    }

    fun increaseInteractionCounter() {
        interactionCounter++
    }

}