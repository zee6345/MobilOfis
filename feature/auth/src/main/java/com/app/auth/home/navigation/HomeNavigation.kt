package com.app.auth.home.navigation

import MainScreenView
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.auth.home.adjustments.ForgetPasswordScreen
import com.app.auth.home.adjustments.SecurityScreen
import com.app.auth.home.adjustments.companies.companyDisplay.CompanyDisplay
import com.app.auth.login.home.AdjustmentsScreen

const val homeScreenRoute = "homeSCreen"
const val changePassScreen = "changePassSCreen"
const val adjustmentScreen = "adjustmentScreen"
const val securityScreen = "securityScreen"
const val displayDuringLogin = "companyDisplayLogin"


fun NavGraphBuilder.homeScreen(
    navController: NavController
) {
    composable(
        route = homeScreenRoute,
    ) {
        MainScreenView(navController)
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

fun NavGraphBuilder.companyDisplayDuringLogin(
    navController: NavController
) {
    composable(
        route = displayDuringLogin,
    ) {
        CompanyDisplay(navController)
    }
}