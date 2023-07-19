package com.app.adjustment.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.adjustment.AdjustmentsScreen
import com.app.adjustment.ForgetPasswordScreen
import com.app.adjustment.SecurityScreen

const val displayDuringLogin = "companyDisplayLogin"
const val adjustmentScreen = "adjustmentScreen"
const val securityScreen = "securityScreen"
const val changePassScreen = "changePassSCreen"


fun NavGraphBuilder.companyDisplayDuringLogin(
    navController: NavController
) {
    composable(
        route = displayDuringLogin,
    ) {
        com.app.adjustment.companies.companyDisplay.CompanyDisplay(navController)
    }
}


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

fun NavGraphBuilder.changePasswordScreen(
    navController: NavController
) {
    composable(
        route = changePassScreen,
    ) {
        ForgetPasswordScreen(navController)
    }
}