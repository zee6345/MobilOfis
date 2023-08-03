package com.app.network.models.responseModels

data class VerifyChangePasswordResponse(
    val resultCode: Int?,
    val errCode: Int,
    val errMessage: String,
    val gniAuthResponseType: GniAuthResponseType?
)
