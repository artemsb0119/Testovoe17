package com.example.testovoe17

import android.app.Application
import com.onesignal.OneSignal

const val ONESIGNAL_APP_ID = "0068d732-fde5-4cd5-9e91-e03e58276558"

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()

        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }
}