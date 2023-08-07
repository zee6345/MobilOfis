package com.app.network.models.responseModels.transferModels

class PaymentSubjectResponse : ArrayList<PaymentSubjectResponseItem>()

data class PaymentSubjectResponseItem(
    val CODE: String,
    val NAME: String
)