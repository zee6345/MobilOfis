package com.app.network.models.responseModels.transferModels

class TransferListResponse : ArrayList<TransferListResponseItem>()

data class TransferListResponseItem(
    val actionButtons: ActionButtons,
    val amount: Double,
    val approveAvailable: String,
    val benefName: String,
    val brTrnType: String,
    val commamount: Double,
    val currency: String,
    val customerAccount: String,
    val description: String,
    val errorText: String,
    val ibankRef: String,
    val signAvailable: String,
    val signDetailList: List<SignDetail>,
    val status: String,
    val statusDescription: Any,
    val trnDateTime: String,
    val trnType: String
)

data class ActionButtons(
    val adocButton: Boolean,
    val approveButton: Boolean,
    val closeButton: Boolean,
    val deleteButton: Boolean,
    val editButton: Boolean,
    val excelButton: Boolean,
    val multiplePdfButton: Boolean,
    val repeatButton: Boolean,
    val saveAsTemplateButton: Boolean,
    val sendBankButton: Boolean,
    val signButton: Boolean,
    val singlePdfButton: Boolean,
    val swiftButton: Boolean
)

data class SignDetail(
    val GGLcode: String,
    val SMScode: String,
    val fio: String,
    val serialNumber: Any,
    val signDate: String,
    val signLevel: Int,
    val signType: String,
    val signerFin: Any,
    val signerUser: String
)

