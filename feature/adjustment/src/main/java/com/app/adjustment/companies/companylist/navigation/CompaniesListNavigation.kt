package com.app.adjustment.companies.companylist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.adjustment.companies.companydisplay.CompanyDisplay
import com.app.adjustment.companies.companylist.Companies


const val companiesDisplayToCompanies = "companiesDisplayToCompanies"

fun NavGraphBuilder.companiesDisplayToCompanies(navController: NavController){

    composable(companiesDisplayToCompanies){
        Companies(navController)
    }

}