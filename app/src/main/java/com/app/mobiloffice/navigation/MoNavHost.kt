package com.app.mobiloffice.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.app.adjustment.changepassword.navigation.changePasswordScreen
import com.app.adjustment.changepin.navigation.changePin
import com.app.adjustment.changepin.navigation.confirmPin
import com.app.adjustment.companies.companydisplay.navigation.companyDisplayDuringLogin
import com.app.adjustment.companies.companylist.navigation.companiesDisplayToCompanies
import com.app.adjustment.exchangerate.navigation.adjustmentToExchangeRate
import com.app.adjustment.navigation.adjustmentScreen

import com.app.adjustment.navigation.securitySCreen
import com.app.adjustment.userprofile.navigation.adjustmentToUserProfile
import com.app.auth.login.navigation.loginScreen
import com.app.auth.login.navigation.otpScreen
import com.app.auth.pin.navigation.pinScreen
import com.app.auth.pin.navigation.resetScreen
import com.app.auth.pin.navigation.successfulRegistrationScreen
import com.app.auth.pin.navigation.welcomePin
import com.app.auth.splash.navigation.splashNavigationRoute
import com.app.auth.splash.navigation.splashScreen
import com.app.home.main.account.navigation.accountDetails
import com.app.home.main.cards.navigation.cardDetailsNavigation
import com.app.home.main.loan.navigation.loanInformationNavigation
import com.app.home.main.trust.navigation.trustInformationNavigation
import com.app.home.navigation.homeScreen
import com.app.mobiloffice.ui.MoState
import com.app.transfer.transfers.navigation.transferDetailsNavigation


@Composable
fun MoNavHost(
    appState: MoState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = splashNavigationRoute,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        splashScreen(navController)
        loginScreen(navController)
        otpScreen(navController)
        pinScreen(navController)
        resetScreen(navController)
        successfulRegistrationScreen(navController)
        welcomePin(navController)
        homeScreen(navController)
        changePasswordScreen(navController)
        companyDisplayDuringLogin(navController)
        companiesDisplayToCompanies(navController)
        adjustmentScreen(navController)
        securitySCreen(navController)
        accountDetails(navController)
        cardDetailsNavigation(navController)
        loanInformationNavigation(navController)
        trustInformationNavigation(navController)
        transferDetailsNavigation(navController)
        adjustmentToUserProfile(navController)
        changePin(navController)
        confirmPin(navController)
        adjustmentToExchangeRate(navController)
    }
}


