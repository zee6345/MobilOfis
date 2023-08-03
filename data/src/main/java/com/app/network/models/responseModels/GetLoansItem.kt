package com.app.network.models.responseModels

data class GetLoansItem(
    val BRANCH_NAME: String,
    val CCY_NAME: String,
    val CONTRACT: String,
    val CREDIT_AMOUNT: Double,
    val CREDIT_NAME: String,
    val CUR_INT_AMOUNT: Double,
    val DELAY_DAY_COUNT: Int,
    val END_DATE: String,
    val INT_RATE: Double,
    val MAIN_BALANCE: Double,
    val NEXT_PAYMENT_AMOUNT: Double,
    val NEXT_PAYMENT_DATE: String,
    val PAID_TOTAL_MONTH: String,
    val PAYMENT_ACCOUNT: String,
    val PAYMENT_ACC_BALANCE: Double,
    val SENED_NO: String,
    val START_DATE: String,
    val STATUS: String,
    val TOTAL_DELAY_AMOUNT: Double,
    val iban: String,
    val nickName: Any
)