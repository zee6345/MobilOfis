package com.app.auth.splash.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
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
            delay(2000) // 5000 milliseconds = 5 seconds delay


//            val session = Session()
//            val token = session["token"]
//            val pin = session["finalPin"]
//
//            if (pin.isNullOrEmpty()) {
//                if (!token.isNullOrEmpty()) {
//                    // If pin is empty and token is not empty, move to the welcome screen.
//                    navController.navigate(route = pinNavigationRoute) {
//                        popUpTo(splashNavigationRoute) { inclusive = true }
//                    }
//                } else {
//                    // If both pin and token are empty, move to the login screen.
//                    navController.navigate(route = loginNavigationRoute) {
//                        popUpTo(splashNavigationRoute) { inclusive = true }
//                    }
//                }
//            } else {
//                // If pin is not empty, move to the pin screen.
//                navController.navigate(route = welcomePinScreen) {
//                    popUpTo(splashNavigationRoute) { inclusive = true }
//                }
//            }


            navController.navigate(route = loginNavigationRoute) {
                popUpTo(splashNavigationRoute) { inclusive = true }
            }

//            navController.navigate(route = homeScreenRoute) {
//                popUpTo(splashNavigationRoute) { inclusive = true }
//            }

        }
    }

}