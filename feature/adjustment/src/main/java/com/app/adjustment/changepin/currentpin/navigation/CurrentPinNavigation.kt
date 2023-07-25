package com.app.adjustment.changepin.currentpin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.adjustment.changepin.currentpin.CurrentPin


const val adjustmentToCurrentPin = "homeToCurrentPin"

fun NavGraphBuilder.adjustmentToCurrentPin(
    navController: NavController
) {
    composable(
        route = adjustmentToCurrentPin,
    ) {
        CurrentPin(navController)
    }
}