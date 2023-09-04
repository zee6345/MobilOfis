package com.app.network.models.responseModels

data class GetAccountBlocksItem(
    val blockAmount: Double,
    val blockDate: String,
    val blockId: Int,
    val branchName: String,
    val branchNo: Int,
    val customerNo: Int,
    val description: String,
    val iban: String
)