package com.app.adjustment.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.adjustment.AdjustmentsScreen
import com.app.adjustment.changepassword.ForgetPasswordScreen
import com.app.adjustment.security.SecurityScreen


const val adjustmentScreen = "adjustmentScreen"
const val securityScreen = "securityScreen"


fun NavGraphBuilder.adjustmentScreen(
    navController: NavController
) {
    composable(
        route = adjustmentScreen,
    ) {
        AdjustmentsScreen(navController)
    }
}

fun NavGraphBuilder.securitySCreen(
    navController: NavController
) {
    composable(
        route = securityScreen,
    ) {
        SecurityScreen(navController)
    }
}

