package com.app.mobiloffice.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.app.auth.login.easysignature.navigation.easySignature
import com.app.auth.login.navigation.loginScreen
import com.app.auth.login.navigation.otpScreen
import com.app.transfer.signatureauth.navigation.signAuth
import com.app.auth.pin.navigation.pinScreen
import com.app.auth.pin.navigation.resetScreen
import com.app.auth.pin.navigation.successfulRegistrationScreen
import com.app.auth.pin.navigation.welcomePin
import com.app.auth.splash.navigation.splashNavigationRoute
import com.app.auth.splash.navigation.splashScreen
import com.app.home.navigation.homeScreen
import com.app.mobiloffice.ui.MoState
import com.app.transfer.signatureauth.navigation.signAsanImza
import com.app.transfer.signatureauth.navigation.signAuthGoogle
import com.app.transfer.signatureauth.navigation.signFailed
import com.app.transfer.signatureauth.navigation.signSuccess
import com.app.transfer.signatureauth.navigation.signingHome


@Composable
fun MoNavHost(
    appState: MoState,
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
        easySignature(navController)
        otpScreen(navController)
        pinScreen(navController)
        resetScreen(navController)
        successfulRegistrationScreen(navController)
        welcomePin(navController)
        homeScreen(navController)



        signingHome(navController)
        signAuth(navController)
        signAuthGoogle(navController)
        signAsanImza(navController)
        signSuccess(navController)
        signFailed(navController)
    }
}


