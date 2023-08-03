package com.app.network.models.responseModels

data class AccountNickNameResponse(
    val code: String,
    val detail: String,
    val ibankRef: String?,
    val verfication: String?,
    val uniqueRequestId: String?,
    val cutoff: String?,
    val messages: List<Message>,
    val trnDetails: String?
)
