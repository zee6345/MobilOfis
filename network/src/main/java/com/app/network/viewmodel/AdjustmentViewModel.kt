package com.app.network.viewmodel

import androidx.lifecycle.ViewModel
import com.app.network.data.DataState
import com.app.network.repository.AdjustmentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdjustmentViewModel  : ViewModel()  {

    private val repository: AdjustmentRepository = AdjustmentRepository()
    private val _data = MutableStateFlow<DataState<Any>?>(null)
    val data: MutableStateFlow<DataState<Any>?> get() = _data

    fun getUserInfo(token: String, customerId: String) {
        _data.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val post = async { repository.getUserInfo(token, customerId) }
                withContext(Dispatchers.Main) {
                    _data.value = DataState.Success(post.await())
                }
            } catch (e: Exception) {
                _data.value = DataState.Error(e.message.toString())
            }
        }
    }

}