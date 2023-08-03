package com.app.network.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.network.models.DataState
import com.app.network.models.requestModels.LoginAsanRequest
import com.app.network.models.requestModels.LoginRequest
import com.app.network.models.requestModels.LoginVerificationRequest
import com.app.network.models.responseModels.LoginAsanResponse
import com.app.network.models.responseModels.LoginResponse
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.models.responseModels.VerifyChangePasswordResponse
import com.app.network.helper.Error
import com.app.network.helper.Keys
import com.app.network.helper.Session
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


    private val _asanLogin = MutableStateFlow<DataState<Any>?>(null)
    val asanLogin: MutableStateFlow<DataState<Any>?> get() = _asanLogin


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
                session[Keys.KEY_TOKEN]!!,
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


//    fun changePasswordVerification(verifyChangePasswordRequest: VerifyChangePasswordRequest) {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val post = repository.changePasswordVerify(verifyChangePasswordRequest)
//                withContext(Dispatchers.Main) {
//                    changePasswordVerification.value = post
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//                // Handle error here, maybe update a separate error LiveData
//            }
//        }
//    }


}