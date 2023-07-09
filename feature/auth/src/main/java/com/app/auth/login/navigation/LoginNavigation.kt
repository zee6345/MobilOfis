package com.app.auth.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.auth.login.LoginScreen

const val loginNavigationRoute = "login"

fun NavGraphBuilder.loginScreen(
    navController: NavController
) {
    composable(
        route = loginNavigationRoute,
    ) {
        LoginScreen(navController)
    }
}