package com.app.network.models.responseModels

data class GetExchangeRatesItem(
    val buyTrend: Int,
    val buyrate: Double,
    val cbTrend: Int,
    val cbrate: Double,
    val ccy: String,
    val ccyName: String,
    val ratedate: String,
    val sellTrend: Int,
    val sellrate: Double
)