package com.app.auth.splash.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.auth.home.adjustments.navigation.homeScreenRoute
import com.app.auth.login.navigation.loginNavigationRoute
import com.app.auth.splash.SplashScreen
import kotlinx.coroutines.delay


const val splashNavigationRoute = "splash"

fun NavGraphBuilder.splashScreen(
    navController: NavController
) {
    composable(
        route = splashNavigationRoute,
    ) {
        SplashScreen(navController = navController)

        LaunchedEffect(Unit) {
            delay(5000) // 5000 milliseconds = 5 seconds delay

            navController.navigate(route = loginNavigationRoute) {
                popUpTo(splashNavigationRoute) { inclusive = true }
            }

//            navController.navigate(route = homeScreenRoute) {
//                popUpTo(splashNavigationRoute) { inclusive = true }
//            }
        }
    }

}