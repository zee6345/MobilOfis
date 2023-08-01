package com.app.network.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.network.data.DataState
import com.app.network.data.callModels.ChangePasswordRequest
import com.app.network.data.callModels.VerifyChangePasswordRequest
import com.app.network.data.responseModels.ChangePasswordResponse
import com.app.network.data.responseModels.GetExchangeRates
import com.app.network.data.responseModels.GetUserProfile
import com.app.network.data.responseModels.VerifyChangePasswordResponse
import com.app.network.helper.Error
import com.app.network.helper.Keys
import com.app.network.helper.MainApp
import com.app.network.repository.AdjustmentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdjustmentViewModel : ViewModel() {

    private val repository: AdjustmentRepository = AdjustmentRepository()

    private val _userInfo = MutableStateFlow<DataState<Any>?>(null)
    val userInfo: MutableStateFlow<DataState<Any>?> get() = _userInfo

    private val _disable2FA = MutableStateFlow<DataState<Any>?>(null)
    val disable2FA: MutableStateFlow<DataState<Any>?> get() = _disable2FA

    private val _exchangeRates = MutableStateFlow<DataState<Any>?>(null)
    val exchangeRates: MutableStateFlow<DataState<Any>?> get() = _exchangeRates

    private val _changePassword = MutableStateFlow<DataState<Any>?>(null)
    val changePassword: MutableStateFlow<DataState<Any>?> get() = _changePassword

    private val _verifyChangePassword = MutableStateFlow<DataState<Any>?>(null)
    val verifyChangePassword: MutableStateFlow<DataState<Any>?> get() = _verifyChangePassword


    fun getUserInfo(customerId: String) {
        _userInfo.value = DataState.Loading

        viewModelScope.launch {

            repository.getUserInfo(MainApp.session[Keys.KEY_TOKEN]!!, customerId)
                .enqueue(object : Callback<GetUserProfile> {
                    override fun onResponse(
                        call: Call<GetUserProfile>,
                        response: Response<GetUserProfile>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
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

    fun disable2FA() {
        _disable2FA.value = DataState.Loading

        viewModelScope.launch {

            repository.disable2FA(MainApp.session[Keys.KEY_TOKEN]!!)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _disable2FA.value = DataState.Success(response.body()!!)
                        } else {
                            _disable2FA.value = DataState.Success(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        _disable2FA.value = DataState.Error(Error.handleException(t))
                    }

                })
        }
    }

    fun getExchangeRates() {
        _exchangeRates.value = DataState.Loading

        viewModelScope.launch {

            repository.getExchangeList(MainApp.session[Keys.KEY_TOKEN]!!)
                .enqueue(object : Callback<GetExchangeRates> {
                    override fun onResponse(
                        call: Call<GetExchangeRates>,
                        response: Response<GetExchangeRates>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _exchangeRates.value = DataState.Success(response.body()!!)
                        } else {
                            _exchangeRates.value = DataState.Success(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<GetExchangeRates>, t: Throwable) {
                        _exchangeRates.value = DataState.Error(Error.handleException(t))
                    }

                })
        }
    }

    fun changePassword(changePasswordRequest: ChangePasswordRequest) {
        _changePassword.value = DataState.Loading
        viewModelScope.launch {
            repository.changePasswordRequest(MainApp.session[Keys.KEY_TOKEN]!!, changePasswordRequest).enqueue(object :Callback<ChangePasswordResponse>{
                override fun onResponse(
                    call: Call<ChangePasswordResponse>,
                    response: Response<ChangePasswordResponse>
                ) {
                    if (response.isSuccessful && response.body() != null){
                        _changePassword.value = DataState.Success(response.body()!!)
                    } else {
                        _changePassword.value = DataState.Error(response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<ChangePasswordResponse>, t: Throwable) {
                    _changePassword.value = DataState.Error(Error.handleException(t))
                }

            })

        }
    }

    fun verifyChangePassword(verifyChangePasswordRequest: VerifyChangePasswordRequest) {
        _verifyChangePassword.value = DataState.Loading
        viewModelScope.launch {
            repository.changePasswordVerify(MainApp.session[Keys.KEY_TOKEN]!!, verifyChangePasswordRequest).enqueue(object :Callback<VerifyChangePasswordResponse>{
                override fun onResponse(
                    call: Call<VerifyChangePasswordResponse>,
                    response: Response<VerifyChangePasswordResponse>
                ) {
                    if (response.isSuccessful && response.body() != null){
                        _verifyChangePassword.value = DataState.Success(response.body()!!)
                    } else {
                        _verifyChangePassword.value = DataState.Error(response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<VerifyChangePasswordResponse>, t: Throwable) {
                    _verifyChangePassword.value = DataState.Error(Error.handleException(t))
                }

            })

        }
    }

}