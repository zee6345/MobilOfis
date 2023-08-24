package com.app.home.main.loan.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.home.main.loan.LoanInformationDetails

const val homeToLoanInformation= "homeToLoanInformation"

fun NavGraphBuilder.loanInformationNavigation(navController: NavController){
    composable(homeToLoanInformation){
        LoanInformationDetails(navController = navController)
    }
}