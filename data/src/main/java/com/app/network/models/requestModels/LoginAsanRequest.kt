package com.app.network.models.requestModels

import com.google.gson.annotations.SerializedName

data class LoginAsanRequest(
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("channel")
    val channel: String

)
