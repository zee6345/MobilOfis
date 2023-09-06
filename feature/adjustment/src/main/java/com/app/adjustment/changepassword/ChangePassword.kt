package com.app.adjustment.changepassword

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePassword : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold { padding ->

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {

                    NavigationGraphChangePassword(navController = rememberNavController())

                }

            }

        }

    }
}


@Composable
private fun NavigationGraphChangePassword(navController: NavHostController) {
    NavHost(navController, startDestination = securityToChangePassword) {

        composable(
            securityToChangePassword,
        ) {
            ForgetPasswordScreen(navController)
        }

        composable(changePasswordToOTP) {
            OtpVerifyScreen(navController)
        }

    }
}