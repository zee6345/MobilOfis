package com.app.network.models.responseModels

data class UserBalance(
    val currencies: List<CurrencyInfo>
)

data class CurrencyInfo(
    val CCY_ID: Int,
    val CCY_NAME: String,
    val BALANCE: String,
    val BALANCE_LC: String,
    val BLOCK_BALANCE: String,
    val BLOCK_BALANCE_LC: String,
    val AVAIL_BALANCE: String,
    val AVAIL_BALANCE_LC: String,
    val WAITING_BALANCE: String,
    val WAITING_BALANCE_LC: String,
    val AFTERAPPROVE_BALANCE: String,
    val AFTERAPPROVE_BALANCE_LC: String
)