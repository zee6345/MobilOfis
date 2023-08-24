package com.app.adjustment.otp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

import com.app.adjustment.otp.OtpVerifyScreen


const val changePasswordToOTP = "changePasswordToOTP"

fun NavGraphBuilder.changePasswordToOTP(navController: NavController){
    composable(
        changePasswordToOTP
    ){
        OtpVerifyScreen(navController)
    }
}