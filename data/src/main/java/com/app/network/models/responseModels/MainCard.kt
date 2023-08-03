package com.app.network.models.responseModels

data class MainCard(
    val AdditionCards: List<Any>,
    val AdditionNumb: Int,
    val Balance: Any,
    val BlncBlockedBy: Any,
    val BlockedBy: String,
    val BlockedDate: Any,
    val CardHolder: String,
    val CardStat: String,
    val Currency: String,
    val EncryptedPan: String,
    val ExpDate: String,
    val Iban: String,
    val MainStatus: String,
    val Name: String,
    val Pan: String,
    val PaymentSys: String,
    val ResidualAmount: Any,
    val nickName: String
)