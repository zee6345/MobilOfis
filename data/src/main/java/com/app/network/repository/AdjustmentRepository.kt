package com.app.network.repository

import com.app.network.retrofitClient.APIService
import com.app.network.models.requestModels.ChangePasswordRequest
import com.app.network.models.requestModels.SetFavCustomer
import com.app.network.models.requestModels.VerifyChangePasswordRequest
import com.app.network.models.requestModels.VerifyRequest
import com.app.network.models.requestModels.VerifySecretRequest
import com.app.network.models.responseModels.ChangePasswordResponse
import com.app.network.models.responseModels.GetExchangeRates
import com.app.network.models.responseModels.GetUserProfile
import com.app.network.models.responseModels.GetVerify2FA
import com.app.network.models.responseModels.VerifyChangePasswordResponse
import okhttp3.ResponseBody
import retrofit2.Call
import javax.inject.Inject

class AdjustmentRepository @Inject constructor(private val apiService: APIService) {

    fun getUserInfo(token: String, customerId: String): Call<GetUserProfile> {
        return apiService.getUserInfo(token, customerId)
    }

    fun disable2FA(token: String): Call<ResponseBody> {
        return apiService.disable2FA(token)
    }

    fun enable2FA(token: String): Call<ResponseBody> {
        return apiService.enable2FA(token)
    }

    fun verify2FA(token: String, verifyRequest: VerifyRequest):Call<GetVerify2FA>{
        return  apiService.verify2FA(token, verifyRequest)
    }

    fun verify2FASecret(token: String, verifyRequest: VerifySecretRequest):Call<GetVerify2FA>{
        return  apiService.verify2FASecret(token, verifyRequest)
    }

    fun getExchangeList(token: String): Call<GetExchangeRates> {
        return apiService.getExchangeList(token)
    }

    fun changePasswordRequest(
        token: String,
        changePasswordRequest: ChangePasswordRequest
    ): Call<ChangePasswordResponse> {
        return apiService.changePassword(token, changePasswordRequest)
    }

    fun changePasswordVerify(
        token: String,
        verifyChangePasswordRequest: VerifyChangePasswordRequest
    ): Call<VerifyChangePasswordResponse> {
        return apiService.changePasswordVerify(token, verifyChangePasswordRequest)
    }

    fun setFavCustomer(token: String, favCustomer: SetFavCustomer): Call<ResponseBody> {
        return apiService.setFavCustomer(token, favCustomer)
    }
}