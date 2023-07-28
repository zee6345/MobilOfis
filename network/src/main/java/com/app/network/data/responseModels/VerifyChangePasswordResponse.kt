package com.app.network.data.responseModels

data class VerifyChangePasswordResponse(
    val resultCode: Int?,
    val errCode: Int,
    val errMessage: String,
    val gniAuthResponseType: GniAuthResponseType?
)
