

package com.app.mobiloffice.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.app.auth.home.adjustments.navigation.adjustmentScreen
import com.app.auth.home.adjustments.navigation.changePasswordScreen
import com.app.auth.home.adjustments.navigation.homeScreen
import com.app.auth.home.adjustments.navigation.securitySCreen
import com.app.auth.login.navigation.loginNavigationRoute
import com.app.auth.login.navigation.loginScreen
import com.app.auth.login.navigation.otpScreen
import com.app.auth.pin.navigation.pinScreen
import com.app.auth.pin.navigation.resetScreen
import com.app.auth.pin.navigation.successfulRegistrationScreen
import com.app.auth.pin.navigation.welcomePin

import com.app.mobiloffice.ui.MoState



@Composable
fun MoNavHost(
    appState: MoState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
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
        otpScreen(navController )
        pinScreen(navController)
        resetScreen(navController)
        successfulRegistrationScreen(navController)
        welcomePin(navController)
        homeScreen(navController)
        changePasswordScreen(navController)
        adjustmentScreen(navController)
        securitySCreen(navController)
    }
}


