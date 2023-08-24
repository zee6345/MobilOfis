package com.app.network.models.responseModels.transferModels

class PaymentType : ArrayList<PaymentTypeItem>()

data class PaymentTypeItem(
    val CODE: String,
    val NAME: String
)