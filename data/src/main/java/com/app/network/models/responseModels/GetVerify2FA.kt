package com.app.network.models.responseModels

data class GetVerify2FA(
    val code: String,
    val cutoff: Any,
    val detail: String,
    val ibankRef: Any,
    val messages: List<Message>,
    val trnDetails: Any,
    val uniqueRequestId: Any,
    val verfication: Any
)