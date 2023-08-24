package com.app.adjustment.changepin.newpin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.adjustment.changepin.newpin.NewPin

const val adjustmentToNewPin = "homeToNewPin"

fun NavGraphBuilder.adjustmentToNewPin(
    navController: NavController
) {
    composable(
        route = adjustmentToNewPin,
    ) {
        NewPin(navController)
    }
}