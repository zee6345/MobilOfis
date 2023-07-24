package com.app.network.repository

import com.app.network.data.callModels.ChangePasswordRequest
import com.app.network.data.callModels.LoginAsanRequest
import com.app.network.data.callModels.LoginRequest
import com.app.network.data.callModels.LoginVerificationRequest
import com.app.network.data.callModels.VerfyChangePasswordRequest
import com.app.network.data.responseModels.ChangePasswordResponse
import com.app.network.data.responseModels.LoginAsanResponse
import com.app.network.data.responseModels.LoginResponse
import com.app.network.data.responseModels.LoginVerificationResponse
import com.app.network.data.responseModels.VerifyChangePasswordResponse
import com.app.network.retrofitClient.BaseRetrofitClient

class LoginRepository: BaseRetrofitClient() {

    suspend fun sendLoginRequestGoogleAuth(loginRequest: LoginRequest): LoginResponse {
        return apiService.loginWithUserName(loginRequest)
    }

    suspend fun sendLoginVerificationRequest(loginVerificationRequest: LoginVerificationRequest): LoginVerificationResponse {
        return apiService.loginVerification(loginVerificationRequest)

    }

    suspend fun sendLoginAsanRequest(loginAsanRequest: LoginAsanRequest): LoginAsanResponse {
        return apiService.loginAsan(loginAsanRequest)
    }

   suspend fun changePasswordRequest(changePasswordRequest: ChangePasswordRequest): ChangePasswordResponse {
       return apiService.changePassword(changePasswordRequest)
    }

    suspend fun changePasswordVerify(verifyChangePasswordRequest: VerfyChangePasswordRequest): VerifyChangePasswordResponse {
        return apiService.changePasswordVerify(verifyChangePasswordRequest)
    }
}