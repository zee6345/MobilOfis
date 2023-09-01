package com.app.network.models.responseModels

data class AdditionCard(
    val BlockedBy: Any,
    val BlockedDate: Any,
    val CardHolder: String,
    val CardStat: String,
    val Currency: String,
    val EncryptedPan: String,
    val ExpDate: String,
    val MainStatus: String,
    val Name: String,
    val Pan: String,
    val PaymSys: Any,
    val nickName: Any
)