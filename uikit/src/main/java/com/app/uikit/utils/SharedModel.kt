package com.app.uikit.utils

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.app.uikit.models.SignatureInfo

class SharedModel : ViewModel() {

    val loginType = mutableStateOf(0)
    val easyVerificationCode = mutableStateOf("")

    val signatureData = mutableStateOf<SignatureInfo?>(null)


    companion object {
        private val instance: SharedModel by lazy { SharedModel() }

        fun init(): SharedModel {
            return instance
        }
    }

}