package com.app.network.data.responseModels

data class LoginAsanResponse (
    val resultCode: Int,
    val errCode: String?,
    val errMessage: String?,
    val gniAuthResponseType: GniAuthResponseType
)

data class GniAuthResponseType(
    val userName: String?,
    val userLongName: String?,
    val customerNo: Long,
    val proxyCustomerNo: String?,
    val phoneNumber: String,
    val personalCode: String?,
    val verification: String?,
    val isTOTPEnabled: Boolean?,
    val TOTPSecret: String?,
    val customers: List<String>?,
    val authType: String?,
    val userId: String,
    val timeStamp: String,
    val certIdentifier: String?,
    val certsList: List<String>?,
    val role: String,
    val totalSignerCount: Int?,
    val totalApproverCount: Int?,
    val clientId: String?,
    val channel: String,
    val authToken: String?,
    val locale: String,
    val module: String?,
    val action: String?,
    val deviceId: String?,
    val ecid: String?,
    val refreshToken: String?,
    val message: String?,
    val registerOTP: String?,
    val apiKey: String?
)