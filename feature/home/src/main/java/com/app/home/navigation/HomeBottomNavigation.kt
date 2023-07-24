package com.app.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.adjustment.AdjustmentsScreen
import com.app.adjustment.changepassword.ForgetPasswordScreen
import com.app.adjustment.changepassword.navigation.securityToChangePassword
import com.app.adjustment.companies.companydisplay.CompanyDisplay
import com.app.adjustment.companies.companydisplay.navigation.displayDuringLogin
import com.app.adjustment.companies.companylist.Companies
import com.app.adjustment.companies.companylist.navigation.companiesDisplayToCompanies
import com.app.adjustment.exchangerate.ExchangeRatesScreen
import com.app.adjustment.exchangerate.navigation.adjustmentToExchangeRates
import com.app.adjustment.navigation.securityScreen
import com.app.adjustment.security.SecurityScreen
import com.app.adjustment.userprofile.UserProfileScreen
import com.app.adjustment.userprofile.navigation.adjustmentToUserProfile
import com.app.home.BottomNavItem
import com.app.home.menu.MenuScreen
import com.app.home.menu.account.AccountInformation
import com.app.home.menu.account.navigation.accountDetailsRoute
import com.app.home.menu.cards.CardDetails
import com.app.home.menu.cards.navigation.homeToCardDetails
import com.app.home.menu.loan.LoanInformationDetails
import com.app.home.menu.loan.navigation.homeToLoanInformation
import com.app.home.menu.trust.DepositDetails
import com.app.home.menu.trust.navigation.homeToTrustDepositDetails
import com.app.transfer.TransferScreen
import com.app.transfer.transfers.TransferDetailsInformation
import com.app.transfer.transfers.navigation.transferToDetails

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Menu.screen_route) {

        composable(BottomNavItem.Menu.screen_route) {
            MenuScreen(navController)
        }

        composable(BottomNavItem.Transfers.screen_route) {
            TransferScreen(navController)
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

        composable(homeToCardDetails) {
            CardDetails(navController = navController)
        }

        composable(homeToLoanInformation) {
            LoanInformationDetails(navController = navController)
        }

        composable(homeToTrustDepositDetails) {
            DepositDetails(navController = navController)
        }

        composable(transferToDetails) {
            TransferDetailsInformation(navController = navController)
        }

        composable(companiesDisplayToCompanies) {
            Companies(navController = navController)
        }

        composable(adjustmentToUserProfile) {
            UserProfileScreen(navController)
        }

        composable(
            securityToChangePassword,
        ) {
            ForgetPasswordScreen(navController)
        }

        composable(adjustmentToExchangeRates){
            ExchangeRatesScreen(navController)
        }



    }
}