package com.app.auth.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.auth.home.TransferScreen
import com.app.auth.home.adjustments.SecurityScreen
import com.app.auth.home.adjustments.companies.companyDisplay.CompanyDisplay
import com.app.auth.home.menu.accountdetails.AccountInformation
import com.app.auth.home.menu.accountdetails.navigation.accountDetailsRoute
import com.app.auth.home.menu.MenuScreen
import com.app.auth.login.home.AdjustmentsScreen

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
        
        composable(accountDetailsRoute){
            AccountInformation(navController = navController)
        }

    }
}