package com.app.network.models.responseModels

data class FileDetails(
    val fileBase64: String,
    val fileName: String,
    val ibankRef: String,
    val mimeType: String,
    val pmtid: Any,
    val stepNo: Any
)