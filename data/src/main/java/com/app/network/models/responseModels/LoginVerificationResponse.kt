package com.app.network.models.responseModels

import com.google.gson.annotations.SerializedName

data class LoginVerificationResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("detail")
    val detail: String,
    @SerializedName("ibankRef")
    val ibankRef: String?,
    @SerializedName("verification")
    val verification: String?,
    @SerializedName("uniqueRequestId")
    val uniqueRequestId: String?,
    @SerializedName("cutoff")
    val cutoff: String?,
    @SerializedName("messages")
    val messages: List<Message>,
    @SerializedName("trnDetails")
    val trnDetails: String?
)
