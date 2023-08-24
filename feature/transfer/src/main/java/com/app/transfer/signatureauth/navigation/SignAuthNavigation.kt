package com.app.transfer.signatureauth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.transfer.signatureauth.SignAsanImza
import com.app.transfer.signatureauth.SignAuth
import com.app.transfer.signatureauth.SignAuthGoogle
import com.app.transfer.signatureauth.SignFailed
import com.app.transfer.signatureauth.SignSuccess
import com.app.transfer.signatureauth.SigningHome

const val signatureHome = "signatureHome"
const val signatureAuth = "signatureAuth"
const val signatureAuthGoogle = "signatureAuthGoogle"
const val signatureAsanImza = "signatureAsanImza"
const val signatureSuccess = "signatureSuccess"
const val signatureFailed = "signatureFailed"


fun NavGraphBuilder.signingHome(
    navController: NavController
) {
    composable(
        route = signatureHome,
    ) {
        SigningHome(navController)
    }
}


fun NavGraphBuilder.signAuth(
    navController: NavController
) {
    composable(
        route = signatureAuth,
    ) {
        SignAuth(navController)
    }
}

fun NavGraphBuilder.signAuthGoogle(
    navController: NavController
) {
    composable(
        route = signatureAuthGoogle,
    ) {
        SignAuthGoogle(navController)
    }
}


fun NavGraphBuilder.signAsanImza(
    navController: NavController
) {
    composable(
        route = signatureAsanImza,
    ) {
        SignAsanImza(navController)
    }
}

fun NavGraphBuilder.signSuccess(
    navController: NavController
) {
    composable(
        route = signatureSuccess,
    ) {
        SignSuccess(navController)
    }
}

fun NavGraphBuilder.signFailed(
    navController: NavController
) {
    composable(
        route = signatureFailed,
    ) {
        SignFailed(navController)
    }
}