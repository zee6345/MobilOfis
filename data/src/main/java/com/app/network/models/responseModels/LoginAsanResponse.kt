package com.app.network.models.responseModels

data class LoginAsanResponse(
    val errCode: Any,
    val errMessage: String,
    val gniAuthResponseType: GniAuthResponseType,
    val resultCode: Int
)