package com.app.network.repository

import com.app.network.models.requestModels.LoginAsanRequest
import com.app.network.models.requestModels.LoginRequest
import com.app.network.models.requestModels.LoginVerificationRequest
import com.app.network.models.responseModels.GetLastLogin
import com.app.network.models.responseModels.GetStartMessage
import com.app.network.models.responseModels.LoginAsanResponse
import com.app.network.models.responseModels.LoginResponse
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.retrofitClient.APIService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
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

    fun lastLogin(token: String): Call<GetLastLogin> {
        return apiService.getLastLogin(token)
    }

    fun getDashBoardMessage(): Call<GetStartMessage> {
        return apiService.getDashBoardMessage()
    }


}