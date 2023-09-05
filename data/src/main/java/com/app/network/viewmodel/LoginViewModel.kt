package com.app.network.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.network.helper.Error
import com.app.network.helper.Keys
import com.app.network.helper.Session
import com.app.network.models.DataState
import com.app.network.models.requestModels.LoginAsanRequest
import com.app.network.models.requestModels.LoginRequest
import com.app.network.models.requestModels.LoginVerificationRequest
import com.app.network.models.responseModels.GetLastLogin
import com.app.network.models.responseModels.GetStartMessage
import com.app.network.models.responseModels.LoginAsanResponse
import com.app.network.models.responseModels.LoginResponse
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val _session: Session
) : ViewModel() {

    val session get() = _session

    private val _data = MutableStateFlow<DataState<Any>?>(null)
    val data: MutableStateFlow<DataState<Any>?> get() = _data

    private val _otp = MutableStateFlow<DataState<Any>?>(null)
    val otp: MutableStateFlow<DataState<Any>?> get() = _otp

    private val _asanLogin = MutableStateFlow<DataState<Any>?>(null)
    val asanLogin: MutableStateFlow<DataState<Any>?> get() = _asanLogin

    private val _getDashboardMessage = MutableStateFlow<DataState<Any>?>(null)
    val getDashboardMessage: MutableStateFlow<DataState<Any>?> get() = _getDashboardMessage

//    private val _lastLogin = MutableLiveData<DataState<Any>?>(null)
//    val lastLogin: LiveData<DataState<Any>?> get() = _lastLogin

    private val _lastLogin = MutableStateFlow<DataState<Any>?>(null)
    val lastLogin: MutableStateFlow<DataState<Any>?> get() = _lastLogin


    fun loginWithUserName(loginRequest: LoginRequest) {
        _data.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {

            repository.sendLoginRequestGoogleAuth(loginRequest)
                .enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null) {

                            _data.value = DataState.Success(response.body()!!)

                        } else {
                            _data.value = DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        _data.value = DataState.Error(Error.handleException(t))
                    }

                })
        }
    }


    fun loginAuthVerification(loginVerificationRequest: LoginVerificationRequest) {
        _otp.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {

            repository.sendLoginVerificationRequest(
                session[Keys.KEY_TOKEN]!!,
                loginVerificationRequest
            )
                .enqueue(object : Callback<LoginVerifyResponse> {
                    override fun onResponse(
                        call: Call<LoginVerifyResponse>,
                        response: Response<LoginVerifyResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _otp.value = DataState.Success(response.body()!!)
                        } else {
                            _otp.value = DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<LoginVerifyResponse>, t: Throwable) {
                        _otp.value = DataState.Error(Error.handleException(t))
                    }

                })
        }
    }

    fun asanLogin(loginAsanRequest: LoginAsanRequest) {
        _asanLogin.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            repository.sendLoginAsanRequest(loginAsanRequest)
                .enqueue(object : Callback<LoginAsanResponse> {
                    override fun onResponse(
                        call: Call<LoginAsanResponse>,
                        response: Response<LoginAsanResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _asanLogin.value = DataState.Success(response.body()!!)
                        } else {
                            _asanLogin.value = DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<LoginAsanResponse>, t: Throwable) {
                        _asanLogin.value = DataState.Error(Error.handleException(t))
                    }

                })

        }
    }


    fun lastLogin() {
        _lastLogin.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            repository.lastLogin(session[Keys.KEY_TOKEN]!!)
                .enqueue(object : Callback<GetLastLogin> {
                    override fun onResponse(
                        call: Call<GetLastLogin>,
                        response: Response<GetLastLogin>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _lastLogin.value = DataState.Success(response.body()!!)
                        } else {
                            _lastLogin.value =DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<GetLastLogin>, t: Throwable) {
                        _lastLogin.value = DataState.Error(Error.handleException(t))
                    }

                })
        }
    }

    fun getDashBoardMessage() {
        _getDashboardMessage.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            repository.getDashBoardMessage().enqueue(object : Callback<GetStartMessage> {
                override fun onResponse(
                    call: Call<GetStartMessage>,
                    response: Response<GetStartMessage>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        _getDashboardMessage.value = DataState.Success(response.body()!!)
                    } else {
                        _getDashboardMessage.value =
                            DataState.Error(response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<GetStartMessage>, t: Throwable) {
                    _getDashboardMessage.value = DataState.Error(Error.handleException(t))
                }

            })
        }

    }

}