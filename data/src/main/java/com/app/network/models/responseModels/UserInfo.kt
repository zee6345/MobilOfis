package com.app.network.models.responseModels

data class UserInfo(
    val customerName: String,
    val customerLastName: String,
    val customerAtaAdi: String,
    val lang: String,
    val userName: String,
    val phoneNumber: String,
    val email: String,
    val TOTPEnabled: String,
    val TOTPChangeDate: String,
    val nonOtpEnabled: String
)
