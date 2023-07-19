package com.app.mobiloffice.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.app.home.menu.accountdetails.navigation.accountDetails
import com.app.home.navigation.adjustmentScreen
import com.app.home.navigation.changePasswordScreen
import com.app.home.navigation.companyDisplayDuringLogin
import com.app.home.navigation.homeScreen
import com.app.home.navigation.securitySCreen
import com.app.auth.login.navigation.loginScreen
import com.app.auth.login.navigation.otpScreen
import com.app.auth.pin.navigation.pinScreen
import com.app.auth.pin.navigation.resetScreen
import com.app.auth.pin.navigation.successfulRegistrationScreen
import com.app.auth.pin.navigation.welcomePin
import com.app.auth.splash.navigation.splashNavigationRoute
import com.app.auth.splash.navigation.splashScreen
import com.app.mobiloffice.ui.MoState


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
        adjustmentScreen(navController)
        securitySCreen(navController)
        accountDetails(navController)
    }
}


