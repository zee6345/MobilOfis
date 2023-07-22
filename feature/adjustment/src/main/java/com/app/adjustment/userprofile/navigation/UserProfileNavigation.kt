package com.app.adjustment.userprofile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.adjustment.userprofile.UserProfileScreen

const val adjustmentToUserProfile = "adjustmentToUserProfile"

fun NavGraphBuilder.adjustmentToUserProfile(navController: NavController){
    composable(adjustmentToUserProfile){
        UserProfileScreen(navController)
    }
}