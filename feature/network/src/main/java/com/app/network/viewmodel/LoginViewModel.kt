package com.app.network.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel (private val repository: LoginRepository) : ViewModel(){
    val loginResponseLiveData = MutableLiveData<LoginResponse>()
    val loginVerificationLiveData = MutableLiveData<LoginVerificationResponse>()
    val loginAsan = MutableLiveData<LoginAsanResponse>()
    val changePassword = MutableLiveData<ChangePasswordResponse>()
    val changePasswordVerification = MutableLiveData<VerifyChangePasswordResponse>()

    fun LoginWithUserName(loginRequest: LoginRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val post = repository.sendLoginRequestGoogleAuth(loginRequest)
                withContext(Dispatchers.Main) {
                    loginResponseLiveData.value = post
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error here, maybe update a separate error LiveData
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