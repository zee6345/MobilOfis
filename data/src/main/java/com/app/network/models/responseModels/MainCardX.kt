package com.app.network.models.responseModels

data class MainCardX(
    val AdditionCards: List<AdditionCard>,
    val AdditionNumb: Int,
    val BlockedBy: String,
    val BlockedDate: Any,
    val Currency: String,
    val IBANStat: String,
    val Iban: String,
    val MainStatus: String,
    val Name: String,
    val OpenDate: String,
    val nickName: String
)