package com.app.adjustment.changepin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.adjustment.changepin.PinScreen
import com.app.adjustment.changepin.RepeatPin

const val homeToChangePin = "homeToChangePin"
const val homeToConfirmPin = "homeToConfirmPin"

fun NavGraphBuilder.changePin(
    navController: NavController
) {
    composable(
        route = homeToChangePin,
    ) {
        PinScreen(navController)
    }
}

fun NavGraphBuilder.confirmPin(
    navController: NavController
) {
    composable(
        route = homeToConfirmPin,
    ) {
        RepeatPin(navController)
    }
}