package com.app.mobiloffice.app

import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.app.adjustment.SESSION
import com.app.mobiloffice.R
import com.app.network.helper.Session
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class App : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        // Check if the message contains data payload
        if (message.data.isNotEmpty()) {


            val isExpired = message.data[getString(R.string.isexpired)]
            val isClear = message.data[getString(R.string.isclear)]
            val isSlow = message.data[getString(R.string.isslow)]

            if (isExpired.equals("true", true)) {

                Session(MobileOffice.context).put(getString(R.string.isexpired), true)

                val intent = Intent()
                intent.action = SESSION
                intent.putExtra("data", getString(R.string.isexpired))
                LocalBroadcastManager.getInstance(MobileOffice.context).sendBroadcast(intent)

            }

            if (isSlow.equals("true", true)){
                Session(MobileOffice.context).put(getString(R.string.isslow), true)

                val intent = Intent()
                intent.action = SESSION
                intent.putExtra("data", getString(R.string.isslow))
                LocalBroadcastManager.getInstance(MobileOffice.context).sendBroadcast(intent)
            }

            if (isClear.equals("true", true)) {
                Session(MobileOffice.context).put(getString(R.string.isexpired), false)
                Session(MobileOffice.context).put(getString(R.string.isslow), false)
            }

        }

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}