package com.app.network.models.responseModels

data class BlockedAccountByIBAN(
    val branchNo: Int,
    val customerNo: Long,
    val iban: String,
    val blockAmount: Double,
    val blockId: Int,
    val blockDate: String,
    val description: String,
    val branchName: String
)
