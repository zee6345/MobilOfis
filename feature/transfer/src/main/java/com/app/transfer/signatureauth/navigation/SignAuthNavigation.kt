package com.app.transfer.signatureauth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.transfer.signatureauth.SignAuth
import com.app.transfer.signatureauth.SignAuthGoogle
import com.app.transfer.signatureauth.SignFailed
import com.app.transfer.signatureauth.SignSuccess

const val signatureAuth = "signatureAuth"
const val signatureAuthGoogle = "signatureAuthGoogle"
const val signatureSuccess = "signatureSuccess"
const val signatureFailed = "signatureFailed"


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