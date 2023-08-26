package com.app.network.models.errorResponse

data class TransactionErrorBody(
    val code: Int,
    val `data`: Any,
    val message: String,
    val status: String
)