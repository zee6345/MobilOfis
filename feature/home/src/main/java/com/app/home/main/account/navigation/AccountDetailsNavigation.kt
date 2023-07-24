package com.app.home.main.account.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.home.main.account.AccountInformation

const val accountDetailsRoute = "accountDetailsInformation"

fun NavGraphBuilder.accountDetails(
    navController: NavController
){
    composable(accountDetailsRoute){
        AccountInformation(navController = navController)
    }
}