package com.app.transfer.signatureauth

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.app.transfer.signatureauth.navigation.signatureAsanImza
import com.app.transfer.signatureauth.navigation.signatureAuth
import com.app.transfer.signatureauth.navigation.signatureAuthGoogle
import com.app.uikit.models.AuthType
import com.app.uikit.utils.SharedModel

@Composable
fun SigningHome(navController: NavController) {

    val signInfo = SharedModel.init().signInfo

    when (signInfo.value.authType) {
        AuthType.SMS -> {
            navController.navigate(signatureAuth)
        }

        AuthType.GOOGLE_AUTH -> {
            navController.navigate(signatureAuthGoogle)
        }

        AuthType.ASAN_IMZA -> {
            navController.navigate(signatureAsanImza)
        }
    }

}