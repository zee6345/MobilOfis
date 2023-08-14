package com.app.uikit.utils

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.app.network.models.responseModels.GetTransactionDetails

class SharedModel:ViewModel() {

    val loginType = mutableStateOf(0)
    val ibankRef = mutableStateOf("")


    companion object {
        private val instance: SharedModel by lazy { SharedModel() }

        fun init(): SharedModel {
            return instance
        }
    }

}