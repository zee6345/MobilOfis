package com.app.auth.pin.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.auth.login.LoginScreen
import com.app.auth.pin.PinScreen

const val pinNavigationRoute = "pin"

fun NavGraphBuilder.pinScreen() {
    composable(
        route = pinNavigationRoute,
    ) {
        PinScreen()
    }
}