package com.app.network.data.callModels

import com.google.gson.annotations.SerializedName

data class LoginVerificationRequest (
    @SerializedName("userName")
    val userName:String,
    @SerializedName("verfication")
    val verfication:Int,
    @SerializedName("channel")
    val channel:String,

)