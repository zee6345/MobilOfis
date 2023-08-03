package com.app.network.repository

import com.app.network.retrofitClient.APIService
import com.app.network.models.requestModels.LoginAsanRequest
import com.app.network.models.requestModels.LoginRequest
import com.app.network.models.requestModels.LoginVerificationRequest
import com.app.network.models.responseModels.LoginAsanResponse
import com.app.network.models.responseModels.LoginResponse
import com.app.network.models.responseModels.LoginVerifyResponse
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