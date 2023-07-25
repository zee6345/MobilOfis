package com.app.auth.splash.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.auth.login.navigation.loginNavigationRoute
import com.app.auth.pin.navigation.pinNavigationRoute
import com.app.auth.pin.navigation.welcomePinScreen
import com.app.auth.splash.SplashScreen
import com.app.network.helper.MainApp
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
            delay(4000) // 5000 milliseconds = 5 seconds delay

            val token = MainApp.session["token"]
            val pin = MainApp.session["finalPin"]

            if (pin.isNullOrEmpty()) {
                if (!token.isNullOrEmpty()) {
                    // If pin is empty and token is not empty, move to the welcome screen.
                    navController.navigate(route = pinNavigationRoute) {
                        popUpTo(splashNavigationRoute) { inclusive = true }
                    }
                } else {
                    // If both pin and token are empty, move to the login screen.
                    navController.navigate(route = loginNavigationRoute) {
                        popUpTo(splashNavigationRoute) { inclusive = true }
                    }
                }
            } else {
                // If pin is not empty, move to the pin screen.
                navController.navigate(route = welcomePinScreen) {
                    popUpTo(splashNavigationRoute) { inclusive = true }
                }
            }


//            if (!token.isNullOrEmpty()) {
//                navController.navigate(route = pinNavigationRoute) {
//                    popUpTo(splashNavigationRoute) { inclusive = true }
//                }
//            } else if (!pin.isNullOrEmpty()){
//                navController.navigate(route = welcomePinScreen) {
//                    popUpTo(splashNavigationRoute) { inclusive = true }
//                }
//            } else {
//                navController.navigate(route = loginNavigationRoute) {
//                    popUpTo(splashNavigationRoute) { inclusive = true }
//                }
//            }


//            navController.navigate(route = homeScreenRoute) {
//                popUpTo(splashNavigationRoute) { inclusive = true }
//            }
        }
    }

}