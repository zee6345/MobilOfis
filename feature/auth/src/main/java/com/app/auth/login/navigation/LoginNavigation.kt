package com.app.auth.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.auth.login.LoginScreen
import com.app.auth.login.OtpScreen
import com.app.auth.pin.PinScreen
import com.app.auth.pin.ResetPin

const val loginNavigationRoute = "login"
const val otpNavigationRoute="otp"
const val pinNavigationRoute = "pin"
const val resetPinNavigationRoute = "reset_pin"


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
