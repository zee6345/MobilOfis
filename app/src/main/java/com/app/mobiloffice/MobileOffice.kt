package com.app.mobiloffice

import android.app.Application

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MobileOffice :Application(){

    override fun onCreate() {
        super.onCreate()

//        Bugfender.init(this, "y02RAmS0UJWlujzyxzkDa2ph1YuTUWM4", BuildConfig.DEBUG)
//        Bugfender.enableCrashReporting()
//        Bugfender.enableUIEventLogging(this)
//        Bugfender.enableLogcatLogging()

    }
}