package com.app.network.models.responseModels

data class SignApproveResponse (
    var trnRequestType: String?,
    var lastStatus: String?,
    var verfication: String?,
    var resultCode: String?,
    var resultMessage: String?,
    var description: String?,
    var fileDescriptors: List<FileDescriptor>
)

data class FileDescriptor(
    var ibankref: String?,
    var data: String?,
    var mimeType: String?,
    var fileName: String?,
    var txFid: String?,
    var xref: String?,
    var contractNumber: String?,
    var status: String?,
    var message: String?,
    var trnStatus: String?,
    var cutoff: String?
)