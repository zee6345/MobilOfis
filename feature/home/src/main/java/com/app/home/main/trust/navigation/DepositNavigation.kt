package com.app.home.main.trust.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

import com.app.home.main.trust.DepositDetails

const val homeToTrustDepositDetails="homeToTrustDepositDetails"

fun NavGraphBuilder.trustInformationNavigation(navController: NavController){
    composable(homeToTrustDepositDetails){
        DepositDetails(navController = navController)
    }
}