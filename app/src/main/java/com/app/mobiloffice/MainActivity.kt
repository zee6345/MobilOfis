package com.app.mobiloffice

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.app.mobiloffice.ui.MoApp
import com.app.mobiloffice.ui.theme.MobilOfficeTheme


class MainActivity : ComponentActivity() {

    private lateinit var _context: Context

    // Public read-only property to access the context
    val context: Context
        get() = _context

    // Custom setter to set the context
    private fun setContext(context: Context) {
        // Here, you can do additional checks or actions if needed before setting the context.
        _context = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContext(this)


        setContent {
            MobilOfficeTheme {
                MoApp()
            }
        }
    }
}
