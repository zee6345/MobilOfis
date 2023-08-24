package com.app.network.models.responseModels.transferModels

class BankCodesResponse : ArrayList<BankCodesResponseItem>()

data class BankCodesResponseItem(
    val CODE: String,
    val CORR_ACCOUNT: String,
    val CORR_ACC_SUB: String,
    val NAME: String,
    val SW_BIC: String,
    val VOEN: String
)