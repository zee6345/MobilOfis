package com.app.auth.pin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.auth.pin.PinScreen
import com.app.auth.pin.RepeatPin
import com.app.auth.pin.SuccessfulRegistrationScreen
import com.app.auth.pin.WelcomePinScreen

const val pinNavigationRoute = "pin"
const val resetPinNavigationRoute = "reset_pin"
const val successfulRegistration = "successfulRegistration"
const val welcomePinScreen = "welcomePin"


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
        RepeatPin(navController)
    }
}
fun NavGraphBuilder.successfulRegistrationScreen(
    navController: NavController
) {
    composable(
        route = successfulRegistration,
    ) {
        SuccessfulRegistrationScreen(navController)
    }
}
fun NavGraphBuilder.welcomePin(
    navController: NavController
) {
    composable(
        route = welcomePinScreen,
    ) {
        WelcomePinScreen(navController)
    }
}