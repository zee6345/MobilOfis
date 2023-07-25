package com.app.network.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.network.data.DataState
import com.app.network.data.callModels.ChangePasswordRequest
import com.app.network.data.callModels.LoginAsanRequest
import com.app.network.data.callModels.LoginRequest
import com.app.network.data.callModels.LoginVerificationRequest
import com.app.network.data.callModels.VerifyChangePasswordRequest
import com.app.network.data.responseModels.ChangePasswordResponse
import com.app.network.data.responseModels.LoginAsanResponse
import com.app.network.data.responseModels.LoginResponse
import com.app.network.data.responseModels.LoginVerificationResponse
import com.app.network.data.responseModels.VerifyChangePasswordResponse
import com.app.network.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel () : ViewModel(){

    private val repository: LoginRepository = LoginRepository()

    private val _data = MutableStateFlow<DataState<Any>?>(null)
    val data: MutableStateFlow<DataState<Any>?> get() = _data

//    private val _loginData = MutableStateFlow<LoginResponse?>(null)
//    val loginData: MutableStateFlow<LoginResponse?>
//        get() = _loginData

//    val loginResponseLiveData = MutableLiveData<LoginResponse>()
    val loginVerificationLiveData = MutableLiveData<LoginVerificationResponse>()
    val loginAsan = MutableLiveData<LoginAsanResponse>()
    val changePassword = MutableLiveData<ChangePasswordResponse>()
    val changePasswordVerification = MutableLiveData<VerifyChangePasswordResponse>()

    fun loginWithUserName(loginRequest: LoginRequest) {
        _data.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val post = async { repository.sendLoginRequestGoogleAuth(loginRequest) }
                withContext(Dispatchers.Main) {
                    _data.value = DataState.Success(post.await())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _data.value = DataState.Error(e.message.toString())
            }
        }
    }
    fun LoginVerification(loginVerificationRequest: LoginVerificationRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val post = repository.sendLoginVerificationRequest(loginVerificationRequest)
                withContext(Dispatchers.Main) {
                    loginVerificationLiveData.value = post
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error here, maybe update a separate error LiveData
            }
        }
    }

    fun loginAsan(loginAsanRequest: LoginAsanRequest){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val post = repository.sendLoginAsanRequest(loginAsanRequest)
                withContext(Dispatchers.Main) {
                    loginAsan.value = post
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error here, maybe update a separate error LiveData
            }
        }
    }

    fun changePassword(changePasswordRequest: ChangePasswordRequest){
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

    fun changePasswordVerification(verifyChangePasswordRequest: VerifyChangePasswordRequest){
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

}