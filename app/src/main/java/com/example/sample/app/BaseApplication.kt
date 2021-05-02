package com.example.sample.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Base application required for Hilt
 */
@HiltAndroidApp
class BaseApplication: Application()