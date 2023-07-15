package com.app.auth.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.auth.login.navigation.loginNavigationRoute
import com.app.auth.splash.SplashScreen


const val splashNavigationRoute = "splash"

fun NavGraphBuilder.splashScreen(
    navController: NavController
) {
    composable(
        route = splashNavigationRoute,
    ) {
        SplashScreen(navController = navController)

        navController.navigate(route = loginNavigationRoute) {
            popUpTo(splashNavigationRoute) { inclusive = true }
        }

    }

}