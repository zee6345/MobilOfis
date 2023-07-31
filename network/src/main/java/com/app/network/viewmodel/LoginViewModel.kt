package com.app.network.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.network.data.DataState
import com.app.network.data.callModels.ChangePasswordRequest
import com.app.network.data.callModels.LoginAsanRequest
import com.app.network.data.callModels.LoginRequest
import com.app.network.data.callModels.LoginVerificationRequest
import com.app.network.data.callModels.VerifyChangePasswordRequest
import com.app.network.data.responseModels.ChangePasswordResponse
import com.app.network.data.responseModels.LoginAsanResponse
import com.app.network.data.responseModels.LoginResponse
import com.app.network.data.responseModels.LoginVerifyResponse
import com.app.network.data.responseModels.VerifyChangePasswordResponse
import com.app.network.helper.Error
import com.app.network.helper.Keys
import com.app.network.helper.MainApp
import com.app.network.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class LoginViewModel : ViewModel() {

    private val repository: LoginRepository = LoginRepository()

    private val _data = MutableStateFlow<DataState<Any>?>(null)
    val data: MutableStateFlow<DataState<Any>?> get() = _data


    private val _asanLogin = MutableStateFlow<DataState<Any>?>(null)
    val asanLogin: MutableStateFlow<DataState<Any>?> get() = _asanLogin

    val changePassword = MutableLiveData<ChangePasswordResponse>()
    val changePasswordVerification = MutableLiveData<VerifyChangePasswordResponse>()

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

//    fun loginWithUserName(loginRequest: LoginRequest) {
//        _data.value = DataState.Loading
//
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val post = async { repository.sendLoginRequestGoogleAuth(loginRequest) }
//                withContext(Dispatchers.Main) {
//                    _data.value = DataState.Success(post.await())
//                }
//            } catch (e: SocketTimeoutException) {
//                // Handle SocketTimeoutException
//                withContext(Dispatchers.Main) {
//                    _data.value = DataState.Error("Request timeout. Please check your internet connection.")
//                }
//            } catch (e: IOException) {
//                // Handle other network-related exceptions
//                withContext(Dispatchers.Main) {
//                    _data.value = DataState.Error("Network error: ${e.message}")
//                }
//            } catch (e: HttpException) {
//                // Handle HTTP error responses
//                // ...
//            } catch (e: Exception) {
//                // Handle other generic exceptions
//                // ...
//            }
//        }
//    }


    fun loginAuthVerification(loginVerificationRequest: LoginVerificationRequest) {
        _data.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {

            repository.sendLoginVerificationRequest(
                MainApp.session[Keys.KEY_TOKEN]!!,
                loginVerificationRequest
            )
                .enqueue(object : Callback<LoginVerifyResponse> {
                    override fun onResponse(
                        call: Call<LoginVerifyResponse>,
                        response: Response<LoginVerifyResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _data.value = DataState.Success(response.body()!!)
                        } else {
                            _data.value = DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<LoginVerifyResponse>, t: Throwable) {
                        _data.value = DataState.Error(Error.handleException(t))
                    }

                })
        }
    }

    fun asanLogin(loginAsanRequest: LoginAsanRequest) {
        _asanLogin.value = DataState.Loading

        viewModelScope.launch {
            repository.sendLoginAsanRequest(loginAsanRequest).enqueue(object :Callback<LoginAsanResponse>{
                override fun onResponse(
                    call: Call<LoginAsanResponse>,
                    response: Response<LoginAsanResponse>
                ) {
                    if (response.isSuccessful && response.body() != null){
                        _asanLogin.value = DataState.Success(response.body()!!)
                    } else{
                        _asanLogin.value = DataState.Error(response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<LoginAsanResponse>, t: Throwable) {
                    _asanLogin.value = DataState.Error(Error.handleException(t))
                }

            })

        }
    }

    fun changePassword(changePasswordRequest: ChangePasswordRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val post = repository.changePasswordRequest(changePasswordRequest)
                withContext(Dispatchers.Main) {
                    changePassword.value = post
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error here, maybe update a separate error LiveData
            }
        }
    }

    fun changePasswordVerification(verifyChangePasswordRequest: VerifyChangePasswordRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val post = repository.changePasswordVerify(verifyChangePasswordRequest)
                withContext(Dispatchers.Main) {
                    changePasswordVerification.value = post
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error here, maybe update a separate error LiveData
            }
        }
    }

    private fun handleException(throwable: Throwable) {
        when (throwable) {
            is ConnectException -> {
                _data.value = DataState.Error("no internet connection")
            }

            is SocketTimeoutException -> {
                _data.value = DataState.Error("connection timeout")
            }

            is UnknownHostException -> {
                _data.value = DataState.Error("failed to reached network")
            }

            is HttpException -> {
                when (throwable.code()) {
                    401 -> {
                        // HTTP 401 Unauthorized: Invalid credentials
                        _data.value = DataState.Error("Unauthorized: Invalid credentials")
                    }

                    403 -> {
                        // HTTP 403 Forbidden: Access denied
                        _data.value = DataState.Error("Forbidden: Access denied")
                    }

                    404 -> {
                        // HTTP 404 Not Found: Requested resource not found
                        _data.value = DataState.Error("Not Found: Requested resource not found")
                    }
                    // Add more cases for other HTTP error codes if needed
                    else -> {
                        // Handle other HTTP error codes with a generic message
                        _data.value = DataState.Error("Failed to connect to server")
                    }
                }
            }
        }
    }

}