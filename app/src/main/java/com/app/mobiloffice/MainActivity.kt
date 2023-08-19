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
import com.app.mobiloffice.ui.MoApp
import com.app.mobiloffice.ui.theme.MobilOfficeTheme
import com.app.network.viewmodel.LoginViewModel
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.helper.Session
import com.app.network.models.DataState
import com.app.network.models.ErrorResponse
import com.app.network.models.responseModels.GetLastLogin
import com.app.network.retrofitClient.APIService
import com.app.network.retrofitClient.BaseRetrofitClient
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

                        //clear pin
                        Session(context).delete(Keys.KEY_USER_PIN)
                        Session(context).delete(Keys.KEY_TOKEN)

                        val userPing = Session(context)[Keys.KEY_USER_PIN]
                        val token = Session(context)[Keys.KEY_TOKEN]

                        Log.e("mmmTAG", "${userPing}")
                        Log.e("mmmTAG", "${token}")



//                        val intent = Intent(this@MainActivity, MainActivity::class.java)
//                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                        startActivity(intent)
//                        finishAffinity()

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
        }, 0, 30000)

        viewModel.lastLogin.value?.let {
            when (it) {
                is DataState.Loading -> {

                }

                is DataState.Error -> {


//                    val errorResponse =
//                        Converter.fromJson(it.errorMessage, ErrorResponse::class.java)

//                    Log.e("mmmTAG", errorResponse.code)

//                    if (errorResponse.code.equals("ERROR.SESSION_EXPIRE", true)){
//
//                        // In your sending code
//                        val intent = Intent()
//                        intent.action = SESSION
//                        intent.putExtra("data", "expire")
//                        LocalBroadcastManager.getInstance(this@MainActivity).sendBroadcast(intent)
//
//                    }

                    Log.e("mmmTAG", "error:: ${it.errorMessage}")
                }

                is DataState.Success -> {
                    Log.e("mmmTAG", "success:: ${it.data}")
//                    val code = it.data as Int
//                    when (code) {
//
//                        200 -> {
//
//                        }
//
//                        401 -> {
//
////                            // In your sending code
////                            val intent = Intent()
////                            intent.action = SESSION
////                            intent.putExtra("data", "expire")
////                            LocalBroadcastManager.getInstance(this@MainActivity)
////                                .sendBroadcast(intent)
//
//
//                            val userPing = Session(this)[Keys.KEY_USER_PIN]
//                            val token = Session(this)[Keys.KEY_TOKEN]
//
//                            Log.e("mmmTAG", "${userPing}")
//                            Log.e("mmmTAG", "${token}")
//
//
//                            //clear pin
//                            Session(this).delete(Keys.KEY_USER_PIN)
//                            Session(this).delete(Keys.KEY_TOKEN)
//
//
//                            Log.e("mmmTAG", "${userPing}")
//                            Log.e("mmmTAG", "${token}")
//
//                        }
//
//                        else -> {
//
//                        }
//                    }
                }
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