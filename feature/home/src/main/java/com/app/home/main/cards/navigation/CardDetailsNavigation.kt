package com.app.home.main.cards.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.home.main.cards.CardDetails

const val homeToCardDetails = "homeToCardDetails"

fun NavGraphBuilder.cardDetailsNavigation(navController: NavController) {
    composable(homeToCardDetails) {
        CardDetails(navController = navController)
    }
}