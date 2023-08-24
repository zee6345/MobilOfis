package com.app.network.models.responseModels.transferModels

class TransferCountSummaryResponse : ArrayList<TransferCountSummaryResponseItem>()

data class TransferCountSummaryResponseItem(
    val count: Int,
    val status: String
)