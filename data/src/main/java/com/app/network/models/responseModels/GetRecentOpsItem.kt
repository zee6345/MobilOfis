package com.app.network.models.responseModels

data class GetRecentOpsItem(
    val account_iban: String,
    val account_nickname: String,
    val account_no: String,
    val account_product: String,
    val amount: String,
    val ibankRef:String,
    val currency_name: String,
    val debit_credit_flag: String,
    val descrption: String,
    val receiver_name: String,
    val trn_date: String,
    val trn_time: String,
    val tx_fid: String
)