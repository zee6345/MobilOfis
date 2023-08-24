package com.app.uikit.models

import com.app.network.models.responseModels.transferModels.TransferListResponseItem

data class SignatureInfo(
    val isSignRequired:Boolean,
    val transfer: TransferListResponseItem
)
