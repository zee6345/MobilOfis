package com.app.mobiloffice.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.app.mobiloffice.ui.theme.MobilOfficeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Splash : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MobilOfficeTheme {

                Scaffold(modifier = Modifier) { padding ->
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(padding)
                    ) {


                        SplashScreen(rememberNavController())


                    }
                }
            }
        }

    }

}