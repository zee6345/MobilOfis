package com.app.mobiloffice.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.app.auth.login.easysignature.navigation.easySignature
import com.app.auth.login.navigation.loginNavigationRoute
import com.app.auth.login.navigation.loginScreen
import com.app.auth.login.navigation.otpScreen
import com.app.auth.pin.navigation.pinScreen
import com.app.auth.pin.navigation.resetScreen
import com.app.auth.pin.navigation.successfulRegistrationScreen
import com.app.auth.pin.navigation.welcomePin
import com.app.home.navigation.homeScreen
import com.app.mobiloffice.ui.MoState


@Composable
fun MoNavHost(
    appState: MoState,
    modifier: Modifier = Modifier,
    startDestination: String = loginNavigationRoute,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {

        loginScreen(navController)
        easySignature(navController)
        otpScreen(navController)
        pinScreen(navController)
        resetScreen(navController)
        successfulRegistrationScreen(navController)
        welcomePin(navController)
        homeScreen(navController)
    }
}


