package com.app.home.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.adjustment.AdjustmentsScreen
import com.app.adjustment.companies.companydisplay.CompanyDisplay
import com.app.adjustment.companies.companydisplay.navigation.displayDuringLogin
import com.app.adjustment.companies.companylist.Companies
import com.app.adjustment.companies.companylist.navigation.companiesDisplayToCompanies
import com.app.adjustment.exchangerate.ExchangeRatesScreen
import com.app.adjustment.exchangerate.navigation.adjustmentToExchangeRates
import com.app.adjustment.navigation.securityScreen
import com.app.adjustment.security.SecurityScreen
import com.app.adjustment.userprofile.UserProfileScreen
import com.app.adjustment.userprofile.adjustmentToUserProfile
import com.app.home.BottomNavItem
import com.app.home.main.MenuScreen
import com.app.home.main.account.AccountInformation
import com.app.home.main.account.accountDetailsRoute
import com.app.home.main.cards.CardDetailsNew
import com.app.home.main.cards.CardDetailsOld
import com.app.home.main.cards.homeToNewCardDetails
import com.app.home.main.cards.homeToOldCardDetails
import com.app.home.main.loan.LoanInformationDetails
import com.app.home.main.loan.homeToLoanInformation
import com.app.home.main.recents.RecentDetailed
import com.app.home.main.recents.RecentTransactions
import com.app.home.main.recents.recentToDetails
import com.app.home.main.recents.recentTransactions
import com.app.home.main.trust.DepositDetails
import com.app.home.main.trust.homeToTrustDepositDetails
import com.app.transfer.TransferScreen
import com.app.transfer.signatureauth.SignAsanImza
import com.app.transfer.signatureauth.SignAuth
import com.app.transfer.signatureauth.SignAuthGoogle
import com.app.transfer.signatureauth.SignFailed
import com.app.transfer.signatureauth.SignSuccess
import com.app.transfer.signatureauth.SigningHome
import com.app.transfer.signatureauth.navigation.signatureAsanImza
import com.app.transfer.signatureauth.navigation.signatureAuth
import com.app.transfer.signatureauth.navigation.signatureAuthGoogle
import com.app.transfer.signatureauth.navigation.signatureFailed
import com.app.transfer.signatureauth.navigation.signatureHome
import com.app.transfer.signatureauth.navigation.signatureSuccess
import com.app.transfer.transfers.TransferDetailsInformation
import com.app.transfer.transfers.navigation.transferToDetails

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Main.screen_route) {

        composable(BottomNavItem.Main.screen_route) {
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

        composable(route = accountDetailsRoute) {
            AccountInformation(navController = navController)
        }

        composable(homeToOldCardDetails) {
            CardDetailsOld(navController = navController)
        }

        composable(homeToNewCardDetails) {
            CardDetailsNew(navController = navController)
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

        composable(adjustmentToExchangeRates) {
            ExchangeRatesScreen(navController)
        }

        composable(adjustmentToExchangeRates) {
            ExchangeRatesScreen(navController)
        }

        composable(recentToDetails) {
            RecentDetailed(navController)
        }

        composable(recentTransactions) {
            RecentTransactions(navController)
        }


    }
}

@Composable
fun NavigationGraphSign(navController: NavHostController) {
    NavHost(navController, startDestination = signatureHome) {

        composable(signatureHome) {
            SigningHome(navController)
        }

        composable(signatureAuth) {
            SignAuth(navController)
        }

        composable(signatureAuthGoogle) {
            SignAuthGoogle(navController)
        }

        composable(signatureAsanImza) {
            SignAsanImza(navController)
        }

        composable(signatureSuccess) {
            SignSuccess(navController)
        }

        composable(signatureFailed) {
            SignFailed(navController)
        }

    }
}