package com.app.network.models.requestModels

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("userName")
    val userName: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("authType")
    val authType: String,
    @SerializedName("channel")
    val channel: String
)