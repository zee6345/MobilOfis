package com.app.mobiloffice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.app.mobiloffice.ui.MoApp
import com.app.mobiloffice.ui.theme.MobilOfficeTheme
import com.app.network.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import java.util.TimerTask


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    private lateinit var _context: Context
//
//    // Public read-only property to access the context
//    val context: Context
//        get() = _context
//
//    // Custom setter to set the context
//    private fun setContext(context: Context) {
//        // Here, you can do additional checks or actions if needed before setting the context.
//        _context = context
//    }

    val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContext(this)

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

    }
}