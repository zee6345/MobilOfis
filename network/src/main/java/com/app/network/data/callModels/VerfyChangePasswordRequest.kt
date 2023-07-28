package com.app.network.data.callModels

import com.google.gson.annotations.SerializedName

data class VerifyChangePasswordRequest(
    @SerializedName("userName")
    val userName:String,
    @SerializedName("verfication")
    val verfication:Int
)
