package com.app.network.models.requestModels

data class SendToBankModel(
    val fileDescriptors: List<FileDescriptor>,
    val trnRequestType: String
)