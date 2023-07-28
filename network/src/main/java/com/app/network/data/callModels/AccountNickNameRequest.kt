package com.app.network.data.callModels

import com.google.gson.annotations.SerializedName

data class AccountNickNameRequest(
    @SerializedName("IBAN")
    val IBAN:String,
    @SerializedName("NICKNAME")
    val NICKNAME:String
)
