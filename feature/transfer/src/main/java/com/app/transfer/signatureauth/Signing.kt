package com.app.transfer.signatureauth

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
import com.app.transfer.signatureauth.auth.SignAsanImza
import com.app.transfer.signatureauth.auth.SignAuth
import com.app.transfer.signatureauth.auth.SignAuthGoogle
import com.app.transfer.signatureauth.message.SignFailed
import com.app.transfer.signatureauth.message.SignSuccess
import com.app.transfer.signatureauth.navigation.signatureAsanImza
import com.app.transfer.signatureauth.navigation.signatureAuth
import com.app.transfer.signatureauth.navigation.signatureAuthGoogle
import com.app.transfer.signatureauth.navigation.signatureFailed
import com.app.transfer.signatureauth.navigation.signatureHome
import com.app.transfer.signatureauth.navigation.signatureSuccess
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Signing : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold() { paddingValue ->
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValue)
                ) {
                    NavigationGraphSign(navController = rememberNavController())
                }
            }

        }
    }


    @Composable
    fun NavigationGraphSign(navController: NavHostController) {
        NavHost(navController, startDestination = signatureHome) {

            composable(signatureHome) {
                SigningHome(navController)
            }

            composable(signatureAuth) {
                SignAuth(navController)
            }

            composable(signatureAuthGoogle) {
                SignAuthGoogle(navController)
            }

            composable(signatureAsanImza) {
                SignAsanImza(navController)
            }

            composable(signatureSuccess) {
                SignSuccess(navController)
            }

            composable(signatureFailed) {
                SignFailed(navController)
            }

        }
    }

}