package com.app.auth.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.auth.login.LoginScreen
import com.app.auth.login.otp.OtpScreen


const val loginNavigationRoute = "login"
const val otpNavigationRoute = "otp"

fun NavGraphBuilder.loginScreen(
    navController: NavController
) {
    composable(
        route = loginNavigationRoute,
    ) {
        LoginScreen(navController)
    }
}

fun NavGraphBuilder.otpScreen(
    navController: NavController
) {
    composable(
        route = otpNavigationRoute,
    ) {
        OtpScreen(navController)
    }
}
