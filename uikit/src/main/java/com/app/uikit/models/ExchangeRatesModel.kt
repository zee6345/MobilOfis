package com.app.uikit.models

data class ExchangeRatesModel(
    val country_name: String,
    val purchase_icon: Int,
    val purchase_rate: String,
    val sale_icon: Int,
    val sale_rate: String,
    val bank_icon: Int,
    val bank_rate: String
)