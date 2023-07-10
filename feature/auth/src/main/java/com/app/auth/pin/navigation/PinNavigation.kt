package com.app.auth.pin.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.auth.pin.PinScreen

const val pinNavigationRoute = "pin"
const val resetPinNavigationRoute = "reset_pin"


fun NavGraphBuilder.pinScreen() {
    composable(
        route = pinNavigationRoute,
    ) {
        PinScreen()
    }
}

fun NavGraphBuilder.resetScreen() {
    composable(
        route = pinNavigationRoute,
    ) {
        PinScreen()
    }
}