package com.app.network.models.requestModels

import com.google.gson.annotations.SerializedName

data class LoginVerificationRequest (
    @SerializedName("userName")
    val userName:String,
    @SerializedName("verfication")
    val verfication:Int,
    @SerializedName("channel")
    val channel:String,

)