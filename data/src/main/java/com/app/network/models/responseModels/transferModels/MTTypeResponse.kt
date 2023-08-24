package com.app.network.models.responseModels.transferModels

class MTTypeResponse : ArrayList<MTTypeResponseItem>()

data class MTTypeResponseItem(
    val CODE: String,
    val NAME: String
)