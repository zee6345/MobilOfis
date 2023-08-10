package com.app.auth.utils

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class SharedModel:ViewModel() {

    val loginType = mutableStateOf(0)

    companion object {
        private val instance: SharedModel by lazy { SharedModel() }

        fun init(): SharedModel {
            return instance
        }
    }

}