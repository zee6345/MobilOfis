package com.app.home.menu.accountdetails.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.home.menu.accountdetails.AccountInformation

const val accountDetailsRoute = "accountDetailsInformation"

fun NavGraphBuilder.accountDetails(
    navController: NavController
){
    composable(accountDetailsRoute){
        AccountInformation(navController = navController)
    }
}