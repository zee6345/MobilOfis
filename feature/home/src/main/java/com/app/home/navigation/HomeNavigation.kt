package com.app.home.navigation

import com.app.home.MainScreenView
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val homeScreenRoute = "homeScreen"

fun NavGraphBuilder.homeScreen(
    navController: NavController
) {
    composable(
        route = homeScreenRoute
    ) {
        MainScreenView(navController)
    }
}





