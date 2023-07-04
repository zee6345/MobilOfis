package com.app.auth.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.auth.login.LoginScreen

const val loginNavigationRoute = "login"

fun NavGraphBuilder.loginScreen() {
    composable(
        route = loginNavigationRoute,
    ) {
        LoginScreen()
    }
}