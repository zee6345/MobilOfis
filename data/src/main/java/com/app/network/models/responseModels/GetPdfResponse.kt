package com.app.network.models.responseModels

data class GetPdfResponse(
    val code: Any,
    val `data`: Data,
    val message: Any,
    val status: String
)