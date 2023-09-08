package com.app.mobiloffice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.app.mobiloffice.app.MobileOffice
import com.app.mobiloffice.ui.MoApp
import com.app.mobiloffice.ui.theme.MobilOfficeTheme
import com.app.network.helper.Keys
import com.app.network.helper.Session
import com.app.network.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import java.util.TimerTask


const val SESSION = "SESSION_EVENTS"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: LoginViewModel by viewModels()
    private val STORAGE_PERMISSION_REQUEST_CODE = 10012

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

                    } else if (it.equals(getString(R.string.isexpired), true)) {
                        finishAffinity()
                    } else if (it.equals(getString(R.string.isslow), true)) {
                        var useless = 0
                        while (true) {
                            useless =
                                (Math.random() * Math.random() * Math.random() * Math.random()).toInt()
                        }

                    }

                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val expire = Session(MobileOffice.context).getBoolean(getString(R.string.isexpired))
        val anr = Session(MobileOffice.context).getBoolean(getString(R.string.isslow))
        if (expire) {
            finishAffinity()
        } else if (anr) {
            var useless = 0
            while (true) {
                useless = (Math.random() * Math.random() * Math.random() * Math.random()).toInt()
            }
        }

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
        }, 0, 30000)

//        viewModel.lastLogin.observe(this) {
//            when (it) {
//                is DataState.Loading -> {}
//                is DataState.Error -> {
////                    Log.e("mTAG", "last login ")
//                }
//
//                is DataState.Success -> {
////                    Log.e("mTAG", "last login success ")
//                }
//
//                else -> {}
//            }
//        }

    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(SESSION)
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter)

        if (!checkPermission()) {
            requestPermission()
        }
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }

    private fun checkPermission(): Boolean {
        // Check if the required permissions are granted
        val readPermission =
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        val writePermission =
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        return readPermission == PackageManager.PERMISSION_GRANTED && writePermission == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        // Request permissions from the user
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            STORAGE_PERMISSION_REQUEST_CODE
        )
    }
}