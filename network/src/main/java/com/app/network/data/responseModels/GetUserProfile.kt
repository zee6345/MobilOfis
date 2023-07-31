package com.app.network.data.responseModels

data class GetUserProfile(
    val TOTPChangeDate: String,
    val TOTPEnabled: String,
    val customerAtaAdi: String,
    val customerLastName: String,
    val customerName: String,
    val email: Any,
    val lang: String,
    val nonOtpEnabled: String,
    val phoneNumber: String,
    val userName: String
)