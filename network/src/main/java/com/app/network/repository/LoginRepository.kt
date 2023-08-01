package com.app.network.repository

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
import com.app.network.helper.Keys
import com.app.network.helper.MainApp
import com.app.network.retrofitClient.BaseRetrofitClient
import retrofit2.Call


class LoginRepository: BaseRetrofitClient() {

    fun sendLoginRequestGoogleAuth(loginRequest: LoginRequest): Call<LoginResponse> {
        return apiService.loginWithUserName(loginRequest)
    }

    fun sendLoginVerificationRequest(token:String, loginVerificationRequest: LoginVerificationRequest): Call<LoginVerifyResponse> {
        return apiService.loginVerification(token, loginVerificationRequest)
    }

    fun sendLoginAsanRequest(loginAsanRequest: LoginAsanRequest): Call<LoginAsanResponse> {
        return apiService.loginAsan(loginAsanRequest)
    }


}