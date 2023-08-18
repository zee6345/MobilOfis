package com.app.home.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.adjustment.AdjustmentsScreen
import com.app.adjustment.changepassword.ForgetPasswordScreen
import com.app.adjustment.changepassword.navigation.securityToChangePassword
import com.app.adjustment.changepin.confirmpin.RepeatPin
import com.app.adjustment.changepin.confirmpin.navigation.adjustmentToConfirmPin
import com.app.adjustment.changepin.currentpin.CurrentPin
import com.app.adjustment.changepin.currentpin.navigation.adjustmentToCurrentPin
import com.app.adjustment.changepin.newpin.NewPin
import com.app.adjustment.changepin.newpin.navigation.adjustmentToNewPin
import com.app.adjustment.companies.companydisplay.CompanyDisplay
import com.app.adjustment.companies.companydisplay.navigation.displayDuringLogin
import com.app.adjustment.companies.companylist.Companies
import com.app.adjustment.companies.companylist.navigation.companiesDisplayToCompanies
import com.app.adjustment.exchangerate.ExchangeRatesScreen
import com.app.adjustment.exchangerate.navigation.adjustmentToExchangeRates
import com.app.adjustment.googleauth.ActivateGoogleAuthenticatorScreen
import com.app.adjustment.googleauth.ConfirmSMSCodeScreen
import com.app.adjustment.googleauth.SMSCodeAuthenticatorScreen
import com.app.adjustment.googleauth.googleAuthToOtpVerify
import com.app.adjustment.googleauth.otpToConfirmGoogleAuthOtp
import com.app.adjustment.googleauth.userProfileToGoogleAuth
import com.app.adjustment.navigation.securityScreen
import com.app.adjustment.otp.OtpVerifyScreen
import com.app.adjustment.otp.navigation.changePasswordToOTP
import com.app.adjustment.security.SecurityScreen
import com.app.adjustment.userprofile.UserProfileScreen
import com.app.adjustment.userprofile.navigation.adjustmentToUserProfile
import com.app.home.BottomNavItem
import com.app.home.main.MenuScreen
import com.app.home.main.account.AccountInformation
import com.app.home.main.account.navigation.accountDetailsRoute
import com.app.home.main.cards.CardDetails
import com.app.home.main.cards.navigation.homeToCardDetails
import com.app.home.main.loan.LoanInformationDetails
import com.app.home.main.loan.navigation.homeToLoanInformation
import com.app.home.main.trust.DepositDetails
import com.app.home.main.trust.navigation.homeToTrustDepositDetails
import com.app.transfer.TransferScreen
import com.app.transfer.transfers.TransferDetailsInformation
import com.app.transfer.transfers.navigation.transferToDetails

@OptIn(ExperimentalAnimationApi::class)
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

        composable(adjustmentToExchangeRates) {
            ExchangeRatesScreen(navController)
        }

        composable(adjustmentToExchangeRates) {
            ExchangeRatesScreen(navController)
        }

        composable(
            adjustmentToCurrentPin,
        ) {
            CurrentPin(navController)
        }


        composable(
            adjustmentToNewPin,
        ) {
            NewPin(navController)
        }

        composable(
            adjustmentToConfirmPin,
        ) {
            RepeatPin(navController)
        }

        composable(changePasswordToOTP) {
            OtpVerifyScreen(navController)
        }

        composable(userProfileToGoogleAuth) {
            ActivateGoogleAuthenticatorScreen(navController)
        }

        composable(googleAuthToOtpVerify) {
            SMSCodeAuthenticatorScreen(navController)
        }

        composable(otpToConfirmGoogleAuthOtp) {
            ConfirmSMSCodeScreen(navController)
        }


    }
}