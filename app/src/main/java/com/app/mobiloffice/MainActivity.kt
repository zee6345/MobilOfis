package com.app.mobiloffice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.app.mobiloffice.ui.MoApp
import com.app.mobiloffice.ui.theme.MobilOfficeTheme
import com.app.network.helper.Keys
import com.app.network.helper.Session
import com.app.network.models.DataState
import com.app.network.retrofitClient.BaseRetrofitClient
import com.app.network.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import java.util.TimerTask


const val SESSION = "SESSION_EVENTS"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: LoginViewModel by viewModels()

    // In your receiving code
    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == SESSION) {
                val data = intent.getStringExtra("data")
                // Handle the received data
                data?.let {

                    if (it.equals("expire", true)) {

                        Toast.makeText(context, "Session expire!", Toast.LENGTH_SHORT).show()

                        //clear old token
                        Session(context).delete(Keys.KEY_TOKEN)

                        //disable login with pin
                        Session(context).put(Keys.KEY_ENABLE_PIN_LOGIN, false)

                        val intent = Intent(this@MainActivity, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finishAffinity()

                    } else if (it.equals("exit", true)) {

                        val intent = Intent(this@MainActivity, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finishAffinity()

                    } else {


                    }

                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MobilOfficeTheme {
                MoApp()
            }
        }

        //schedule token validation
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                viewModel.lastLogin()
            }
        }, 0, 60000)

        viewModel.lastLogin.observe(this) {
            when (it) {
                is DataState.Loading -> {}
                is DataState.Error -> {
                    Log.e("mTAG", "last login ")
                }
                is DataState.Success -> {
                    Log.e("mTAG", "last login success ")
                }
                else -> {}
            }
        }

    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(SESSION)
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter)
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }
}