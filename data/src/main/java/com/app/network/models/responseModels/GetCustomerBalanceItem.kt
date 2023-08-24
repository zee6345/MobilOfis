package com.app.network.models.responseModels

data class GetCustomerBalanceItem(
    val AFTERAPPROVE_BALANCE: String,
    val AFTERAPPROVE_BALANCE_LC: String,
    val AVAIL_BALANCE: String,
    val AVAIL_BALANCE_LC: String,
    val BALANCE: String,
    val BALANCE_LC: String,
    val BLOCK_BALANCE: String,
    val BLOCK_BALANCE_LC: String,
    val CCY_ID: Int,
    val CCY_NAME: String,
    val WAITING_BALANCE: String,
    val WAITING_BALANCE_LC: String
)