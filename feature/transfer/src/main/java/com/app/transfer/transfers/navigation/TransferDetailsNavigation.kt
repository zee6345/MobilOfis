package com.app.transfer.transfers.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.transfer.transfers.TransferDetailsInformation

const val transferToDetails = "transferToDetails"

fun NavGraphBuilder.transferDetailsNavigation(navController: NavController){
    composable(transferToDetails){
        TransferDetailsInformation(navController = navController)
    }
}