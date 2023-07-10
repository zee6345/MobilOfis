package com.app.auth.pin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.auth.pin.PinScreen
import com.app.auth.pin.ResetPin

const val pinNavigationRoute = "pin"
const val resetPinNavigationRoute = "reset_pin"


fun NavGraphBuilder.pinScreen(
    navController: NavController
) {
    composable(
        route = pinNavigationRoute,
    ) {
        PinScreen(navController)
    }
}

fun NavGraphBuilder.resetScreen(
    navController: NavController
) {
    composable(
        route = resetPinNavigationRoute,
    ) {
        ResetPin(navController)
    }
}