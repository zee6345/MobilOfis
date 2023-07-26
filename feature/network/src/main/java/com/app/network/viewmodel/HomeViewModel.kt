package com.app.network.viewmodel

import androidx.lifecycle.ViewModel
import com.app.network.data.DataState
import com.app.network.data.callModels.LoginRequest
import com.app.network.repository.HomeRepository
import com.app.network.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel:ViewModel() {

    private val repository: HomeRepository = HomeRepository()

    private val _data = MutableStateFlow<DataState<Any>?>(null)
    val data: MutableStateFlow<DataState<Any>?> get() = _data

    fun getAccounts(token:String, customerId: Int) {
        _data.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val post = async { repository.getAccounts(token, customerId) }
                withContext(Dispatchers.Main) {
                    _data.value = DataState.Success(post.await())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _data.value = DataState.Error(e.message.toString())
            }
        }
    }
}