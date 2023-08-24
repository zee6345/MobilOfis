package com.app.network.models.responseModels

data class LoginVerifyResponse(
    val customerName: String,
    val customerNo: Int,
    val customers: Customers,
    val day: Int,
    val errMessage: Any,
    val forcePassword: String,
    val isTOTPEnabled: Boolean,
    val lastLoginDate: Any,
    val mSignStatus: Any,
    val permissions: List<Permission>,
    val proxyCustomerNo: Any,
    val result: Int,
    val role: Int,
    val totalApproverCount: Int,
    val totalSignerCount: Int,
    val userName: String,
    val usersName: String,
    val usersSurName: String,
    val verification: Any
)