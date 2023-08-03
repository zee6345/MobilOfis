package com.app.network.models.requestModels

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest (
    @SerializedName("userName")
    val userName:String,
    @SerializedName("oldPassword")
    val oldPassword:String,
    @SerializedName("newPassword")
    val newPassword:String,
    @SerializedName("newPasswordRepeat")
    val newPasswordRepeat:String
)