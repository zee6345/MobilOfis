package com.app.network.apiService

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
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path


interface APIService {
    // Define your API endpoints here
    @POST("auth/login")
    suspend fun loginWithUserName(@Body loginBody: LoginRequest): LoginResponse

    @POST("auth/login/verify")
    suspend fun loginVerification(@Body loginVerification: LoginVerificationRequest): LoginVerifyResponse

    @POST("auth/asan")
    suspend fun loginAsan(@Body loginAsanRequest: LoginAsanRequest): LoginAsanResponse

    @POST("auth/changepassword")
    suspend fun changePassword(@Body changePasswordRequest: ChangePasswordRequest): ChangePasswordResponse

    @POST("auth/changepassword/verify")
    suspend fun changePasswordVerify(verifyChangePasswordRequest: VerifyChangePasswordRequest): VerifyChangePasswordResponse

    // Main

    @GET("customers/{customerId}/accounts")
    suspend fun getAccounts(
        @Header("Auth_token") token: String,
        @Path("customerId") customerId: Int
    ): ResponseBody
}