package com.app.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.adjustment.AdjustmentsScreen
import com.app.adjustment.SecurityScreen
import com.app.adjustment.companies.companyDisplay.CompanyDisplay
import com.app.adjustment.navigation.displayDuringLogin
import com.app.adjustment.navigation.securityScreen

import com.app.home.BottomNavItem

import com.app.home.menu.MenuScreen
import com.app.home.menu.accountdetails.AccountInformation
import com.app.home.menu.accountdetails.navigation.accountDetailsRoute
import com.app.transfer.TransferScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Menu.screen_route) {

        composable(BottomNavItem.Menu.screen_route) {
            MenuScreen(navController)
        }

        composable(BottomNavItem.Transfers.screen_route) {
            TransferScreen()
        }

        composable(BottomNavItem.Adjustments.screen_route) {
            AdjustmentsScreen(navController)
        }

        composable(displayDuringLogin) {
            CompanyDisplay(navController)
        }

        composable(securityScreen) {
            SecurityScreen(navController)
        }

        composable(accountDetailsRoute) {
            AccountInformation(navController = navController)
        }

    }
}