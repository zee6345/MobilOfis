package com.app.adjustment.changepin.confirmpin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.adjustment.changepin.confirmpin.RepeatPin

//import com.app.adjustment.changepin.newpin.RepeatPin


const val adjustmentToConfirmPin = "adjustmentToConfirmPin"

fun NavGraphBuilder.adjustmentToConfirmPin(
    navController: NavController
) {
    composable(
        route = adjustmentToConfirmPin,
    ) {
        RepeatPin(navController)
    }
}