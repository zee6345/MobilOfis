package com.app.network.models.requestModels

data class SignApproveRequest(
    val fileDescriptors: List<FileDescriptor>,
    val trnRequestType: String
)