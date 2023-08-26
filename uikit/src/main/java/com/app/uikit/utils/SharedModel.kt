package com.app.uikit.utils

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.network.models.responseModels.transferModels.TransferCountSummaryResponseItem
import com.app.uikit.models.AuthType
import com.app.uikit.models.SignInfo
import com.app.uikit.models.SignatureInfo

class SharedModel : ViewModel() {

    val loginType = mutableStateOf(0)
    val easyVerificationCode = mutableStateOf("")
    val signatureData = mutableStateOf<SignatureInfo?>(null)
    val signInfo = mutableStateOf(SignInfo(false, AuthType.GOOGLE_AUTH))
    val isForSigning = mutableStateOf(false)

//    val transferHeaders = mutableListOf<TransferCountSummaryResponseItem?>(null)

    val isListEmpty = mutableStateOf(false)


    companion object {
        private val instance: SharedModel by lazy { SharedModel() }

        fun init(): SharedModel {
            return instance
        }
    }

}