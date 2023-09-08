package com.app.mobiloffice.app

import android.app.Application
import android.content.Context

import com.app.mobiloffice.R
import com.app.uikit.utils.Utils
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MobileOffice : Application() {

    companion object {
        lateinit var context: Context
    }



    override fun onCreate() {
        super.onCreate()

        context = this@MobileOffice

        Utils.loadCurrencyData(this)


        FirebaseApp.initializeApp(this)
        FirebaseMessaging.getInstance().subscribeToTopic(getString(R.string.transfer))



    }



}