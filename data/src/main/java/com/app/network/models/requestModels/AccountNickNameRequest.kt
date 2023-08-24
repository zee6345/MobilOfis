package com.app.network.models.requestModels

import com.google.gson.annotations.SerializedName

data class AccountNickNameRequest(
    @SerializedName("IBAN")
    val IBAN:String,
    @SerializedName("NICKNAME")
    val NICKNAME:String
)
