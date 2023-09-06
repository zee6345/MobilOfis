package com.app.network.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.network.models.DataState
import com.app.network.models.requestModels.ChangePasswordRequest
import com.app.network.models.requestModels.VerifyChangePasswordRequest
import com.app.network.models.responseModels.ChangePasswordResponse
import com.app.network.models.responseModels.GetExchangeRates
import com.app.network.models.responseModels.GetUserProfile
import com.app.network.models.responseModels.VerifyChangePasswordResponse
import com.app.network.helper.Error
import com.app.network.helper.Keys
import com.app.network.helper.Session
import com.app.network.models.requestModels.SetFavCustomer
import com.app.network.models.requestModels.VerifyRequest
import com.app.network.models.requestModels.VerifySecretRequest
import com.app.network.models.responseModels.GetVerify2FA
import com.app.network.repository.AdjustmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AdjustmentViewModel @Inject constructor(
    private val repository: AdjustmentRepository,
    private val _session: Session
) : ViewModel() {

    val session get() = _session

    private val _userInfo = MutableStateFlow<DataState<Any>?>(null)
    val userInfo: MutableStateFlow<DataState<Any>?> get() = _userInfo

    private val _disable2FA = MutableStateFlow<DataState<Any>?>(null)
    val disable2FA: MutableStateFlow<DataState<Any>?> get() = _disable2FA

    private val _enable2FA = MutableStateFlow<DataState<Any>?>(null)
    val enable2FA: MutableStateFlow<DataState<Any>?> get() = _enable2FA

    private val _verify2FA = MutableStateFlow<DataState<Any>?>(null)
    val verify2FA: MutableStateFlow<DataState<Any>?> get() = _verify2FA

    private val _verify2FASecret = MutableStateFlow<DataState<Any>?>(null)
    val verify2FASecret: MutableStateFlow<DataState<Any>?> get() = _verify2FASecret

    private val _exchangeRates = MutableStateFlow<DataState<Any>?>(null)
    val exchangeRates: MutableStateFlow<DataState<Any>?> get() = _exchangeRates

    private val _changePassword = MutableStateFlow<DataState<Any>?>(null)
    val changePassword: MutableStateFlow<DataState<Any>?> get() = _changePassword

    private val _verifyChangePassword = MutableStateFlow<DataState<Any>?>(null)
    val verifyChangePassword: MutableStateFlow<DataState<Any>?> get() = _verifyChangePassword


    private val _setFavCustomer = MutableStateFlow<DataState<Any>?>(null)
    val setFavCustomer: MutableStateFlow<DataState<Any>?> get() = _setFavCustomer


    fun getUserInfo(customerId: String) {
        _userInfo.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {

            repository.getUserInfo(session[Keys.KEY_TOKEN]!!, customerId)
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

        CoroutineScope(Dispatchers.IO).launch {

            repository.disable2FA(session[Keys.KEY_TOKEN]!!)
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

    fun enable2FA() {
        _enable2FA.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {

            repository.enable2FA(session[Keys.KEY_TOKEN]!!)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _enable2FA.value = DataState.Success(response.body()!!)
                        } else {
                            _enable2FA.value = DataState.Success(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        _enable2FA.value = DataState.Error(Error.handleException(t))
                    }

                })
        }
    }

    fun verify2FA(verifyRequest: VerifyRequest) {
        _verify2FA.value = DataState.Loading

        repository.verify2FA(session[Keys.KEY_TOKEN]!!, verifyRequest)
            .enqueue(object : Callback<GetVerify2FA> {
                override fun onResponse(
                    call: Call<GetVerify2FA>,
                    response: Response<GetVerify2FA>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        _verify2FA.value = DataState.Success(response.body()!!)
                    } else {
                        _verify2FA.value = DataState.Error(response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<GetVerify2FA>, t: Throwable) {
                    _verify2FA.value = DataState.Error(Error.handleException(t))
                }

            })
    }

    fun verify2FASecret(verifySecretRequest: VerifySecretRequest) {
        _verify2FASecret.value = DataState.Loading

        repository.verify2FASecret(session[Keys.KEY_TOKEN]!!, verifySecretRequest)
            .enqueue(object : Callback<GetVerify2FA> {
                override fun onResponse(
                    call: Call<GetVerify2FA>,
                    response: Response<GetVerify2FA>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        _verify2FASecret.value = DataState.Success(response.body()!!)
                    } else {
                        _verify2FASecret.value = DataState.Error(response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<GetVerify2FA>, t: Throwable) {
                    _verify2FASecret.value = DataState.Error(Error.handleException(t))
                }

            })
    }

    fun getExchangeRates() {
        _exchangeRates.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {

            repository.getExchangeList(session[Keys.KEY_TOKEN]!!)
                .enqueue(object : Callback<GetExchangeRates> {
                    override fun onResponse(
                        call: Call<GetExchangeRates>,
                        response: Response<GetExchangeRates>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _exchangeRates.value = DataState.Success(response.body()!!)
                        } else {
                            _exchangeRates.value =
                                DataState.Success(response.errorBody()!!.string())
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

        CoroutineScope(Dispatchers.IO).launch {
            repository.changePasswordRequest(
                session[Keys.KEY_TOKEN]!!,
                changePasswordRequest
            ).enqueue(object : Callback<ChangePasswordResponse> {
                override fun onResponse(
                    call: Call<ChangePasswordResponse>,
                    response: Response<ChangePasswordResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
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

        CoroutineScope(Dispatchers.IO).launch {
            repository.changePasswordVerify(
                session[Keys.KEY_TOKEN]!!,
                verifyChangePasswordRequest
            ).enqueue(object : Callback<VerifyChangePasswordResponse> {
                override fun onResponse(
                    call: Call<VerifyChangePasswordResponse>,
                    response: Response<VerifyChangePasswordResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        _verifyChangePassword.value = DataState.Success(response.body()!!)
                    } else {
                        _verifyChangePassword.value =
                            DataState.Error(response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<VerifyChangePasswordResponse>, t: Throwable) {
                    _verifyChangePassword.value = DataState.Error(Error.handleException(t))
                }

            })

        }
    }

    fun setFavCustomer(favCustomer: SetFavCustomer) {
        _setFavCustomer.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            repository.setFavCustomer(
                session[Keys.KEY_TOKEN]!!,
                favCustomer
            ).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        _setFavCustomer.value = DataState.Success(response.body()!!)
                    } else {
                        _setFavCustomer.value =
                            DataState.Error(response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    _setFavCustomer.value = DataState.Error(Error.handleException(t))
                }

            })

        }
    }

}