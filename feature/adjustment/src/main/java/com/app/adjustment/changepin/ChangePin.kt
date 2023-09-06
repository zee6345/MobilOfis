package com.app.adjustment.changepin

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
class ChangePin : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {


            Scaffold { padding ->

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {

                    NavigationGraphChangePin(rememberNavController())

                }

            }


        }

    }

}

@Composable
private fun NavigationGraphChangePin(navController: NavHostController) {
    NavHost(navController, startDestination = adjustmentToCurrentPin) {

        composable(
            adjustmentToCurrentPin,
        ) {
            CurrentPin(navController)
        }

        composable(
            adjustmentToNewPin,
        ) {
            NewPin(navController)
        }

        composable(
            adjustmentToConfirmPin,
        ) {
            RepeatPin(navController)
        }
    }
}