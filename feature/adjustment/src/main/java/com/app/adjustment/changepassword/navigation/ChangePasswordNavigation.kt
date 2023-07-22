package com.app.adjustment.changepassword.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.adjustment.changepassword.ForgetPasswordScreen



const val securityToChangePassword = "securityToChangePassword"

fun NavGraphBuilder.changePasswordScreen(
    navController: NavController
) {
    composable(
        route = securityToChangePassword,
    ) {
        ForgetPasswordScreen(navController)
    }
}