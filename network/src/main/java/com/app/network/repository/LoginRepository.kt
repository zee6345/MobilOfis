package com.app.network.repository

import com.app.network.apiService.APIService
import com.app.network.data.callModels.LoginAsanRequest
import com.app.network.data.callModels.LoginRequest
import com.app.network.data.callModels.LoginVerificationRequest
import com.app.network.data.responseModels.LoginAsanResponse
import com.app.network.data.responseModels.LoginResponse
import com.app.network.data.responseModels.LoginVerifyResponse
import retrofit2.Call
import javax.inject.Inject


class LoginRepository @Inject constructor(private val apiService: APIService) {

    fun sendLoginRequestGoogleAuth(loginRequest: LoginRequest): Call<LoginResponse> {
        return apiService.loginWithUserName(loginRequest)
    }

    fun sendLoginVerificationRequest(
        token: String,
        loginVerificationRequest: LoginVerificationRequest
    ): Call<LoginVerifyResponse> {
        return apiService.loginVerification(token, loginVerificationRequest)
    }

    fun sendLoginAsanRequest(loginAsanRequest: LoginAsanRequest): Call<LoginAsanResponse> {
        return apiService.loginAsan(loginAsanRequest)
    }


}