package com.app.network.models.responseModels

data class UserAccountsResponse(
    val CUSTOMER_NO: Long,
    val BRANCH_NO: String,
    val BRANCH_NAME: String,
    val ACCOUNT_NO: String,
    val IBAN: String,
    val ORJ_IBAN: String,
    val CCY_NAME: String,
    val BALANCE: String,
    val BALANCE_LC: String,
    val INTEREST_ACC: String,
    val PRODUCT_CODE: String,
    val PRODUCT_NAME: String,
    val OPENDATE: String,
    val productId: String?, // or Long?
    val ccyId: String?, // or Long?
    val STATUS: String,
    val BLOCKSTATUS: String,
    val BLOCKSAMOUNT: String,
    val NICKNAME: String,
    val REAL_BALANCE: String,
    val WFA_AMOUNT: String,
    val WFA_DEBIT: String,
    val WFA_CREDIT: String,
    val WFA_COMMISSION: String,
    val LAST_BALANCE: String,
    val LAST_DAY_BALANCE: String,
    val DEBIT_BALANCE: String,
    val CREDIT_BALANCE: String,
    val ACCOUNT_TYPE: String
)
