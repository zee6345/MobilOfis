package com.app.network.models.responseModels

data class HISTORYDETAILS(
    val errorText: Any,
    val ibankref: String,
    val transactionDate: String,
    val transactionTime: String,
    val trnRequestType: String,
    val trnStatusAfter: String,
    val trnStatusAfterStr: String,
    val trnStatusBefore: String,
    val trnStatusBeforeStr: String,
    val userLongName: String,
    val userName: String
)