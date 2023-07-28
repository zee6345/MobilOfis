package com.app.network.data.callModels

import com.google.gson.annotations.SerializedName

data class LoginAsanRequest(
    @SerializedName("phoneNumber")
    val phoneNumber: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("channel")
    val channel: String

)
