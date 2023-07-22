package com.app.home.menu.loan.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.home.menu.loan.LoanInformationDetails

const val homeToLoanInformation= "homeToLoanInformation"

fun NavGraphBuilder.loanInformationNavigation(navController: NavController){
    composable(homeToLoanInformation){
        LoanInformationDetails(navController = navController)
    }
}