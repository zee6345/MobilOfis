package com.app.network.apiService

import com.app.network.data.callModels.ChangePasswordRequest
import com.app.network.data.callModels.LoginAsanRequest
import com.app.network.data.callModels.LoginRequest
import com.app.network.data.callModels.LoginVerificationRequest

import com.app.network.data.responseModels.ChangePasswordResponse
import com.app.network.data.responseModels.LoginAsanResponse
import com.app.network.data.responseModels.LoginResponse
import com.app.network.data.responseModels.LoginVerificationResponse
import com.app.network.data.responseModels.VerifyChangePasswordResponse
import com.app.network.data.callModels.VerifyChangePasswordRequest
import retrofit2.http.Body

import retrofit2.http.POST


interface APIService {
    // Define your API endpoints here
    @POST("auth/login")
    suspend fun loginWithUserName(@Body loginBody: LoginRequest): LoginResponse

    @POST("auth/login/verify")
    suspend fun loginVerification(@Body loginVerification: LoginVerificationRequest): LoginVerificationResponse

    @POST("auth/asan")
    suspend fun loginAsan(@Body loginAsanRequest: LoginAsanRequest): LoginAsanResponse

    @POST("auth/changepassword")
    suspend fun changePassword(@Body changePasswordRequest: ChangePasswordRequest): ChangePasswordResponse

    @POST("auth/changepassword/verify")
    suspend fun changePasswordVerify(verifyChangePasswordRequest: VerifyChangePasswordRequest): VerifyChangePasswordResponse
}