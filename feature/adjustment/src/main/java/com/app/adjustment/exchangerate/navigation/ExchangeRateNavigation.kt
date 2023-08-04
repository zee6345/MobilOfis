package com.app.adjustment.exchangerate.navigation

import androidx.navigation.NavController

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.adjustment.exchangerate.ExchangeRatesScreen

const val adjustmentToExchangeRates = "adjustmentToExchangeRates"

fun NavGraphBuilder.adjustmentToExchangeRate(navController: NavController) {
    composable(adjustmentToExchangeRates) {
        ExchangeRatesScreen(navController)
    }
}