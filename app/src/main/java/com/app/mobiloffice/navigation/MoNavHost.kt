

package com.app.mobiloffice.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.app.auth.login.navigation.loginNavigationRoute
import com.app.auth.login.navigation.loginScreen
import com.app.auth.pin.navigation.pinScreen
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
        pinScreen()
    }
}


