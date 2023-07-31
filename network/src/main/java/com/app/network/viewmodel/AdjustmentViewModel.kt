package com.app.network.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.network.data.DataState
import com.app.network.data.responseModels.GetUserProfile
import com.app.network.helper.Error
import com.app.network.helper.Keys
import com.app.network.helper.MainApp
import com.app.network.repository.AdjustmentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdjustmentViewModel  : ViewModel()  {

    private val repository: AdjustmentRepository = AdjustmentRepository()

    private val _userInfo = MutableStateFlow<DataState<Any>?>(null)
    val userInfo: MutableStateFlow<DataState<Any>?> get() = _userInfo

    fun getUserInfo(customerId: String) {
        _userInfo.value = DataState.Loading

        viewModelScope.launch {

            repository.getUserInfo(MainApp.session[Keys.KEY_TOKEN]!!, customerId)
                .enqueue(object :Callback<GetUserProfile>{
                override fun onResponse(
                    call: Call<GetUserProfile>,
                    response: Response<GetUserProfile>
                ) {
                    if (response.isSuccessful && response.body() != null){
                        _userInfo.value = DataState.Success(response.body()!!)
                    } else {
                        _userInfo.value = DataState.Error(response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<GetUserProfile>, t: Throwable) {
                    _userInfo.value = DataState.Error(Error.handleException(t))
                }

            })

        }
    }

}