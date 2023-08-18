package com.app.auth.login.easysignature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.auth.login.easysignature.EasySignatureScreen

const val loginToEasySignature = "loginToEasySignature"

fun NavGraphBuilder.easySignature(
    navController: NavController
) {
    composable(
        route = loginToEasySignature,
    ) {
        EasySignatureScreen(navController)
    }
}