package com.app.network.models

data class TransactionErrorBody(
    val code: Int,
    val `data`: Any,
    val message: String,
    val status: String
)