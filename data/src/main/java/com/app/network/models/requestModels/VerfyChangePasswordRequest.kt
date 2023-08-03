package com.app.network.models.requestModels

import com.google.gson.annotations.SerializedName

data class VerifyChangePasswordRequest(
    @SerializedName("userName")
    val userName:String,
    @SerializedName("verfication")
    val verfication:Int
)
