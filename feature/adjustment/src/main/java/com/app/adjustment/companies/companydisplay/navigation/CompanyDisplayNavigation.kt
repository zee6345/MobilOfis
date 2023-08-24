package com.app.adjustment.companies.companydisplay.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.adjustment.companies.companydisplay.CompanyDisplay


const val displayDuringLogin = "companyDisplayLogin"

fun NavGraphBuilder.companyDisplayDuringLogin(
    navController: NavController
) {
    composable(
        route = displayDuringLogin,
    ) {
        CompanyDisplay(navController)
    }
}