package com.app.network.repository

import com.app.network.data.callModels.ChangePasswordRequest
import com.app.network.data.callModels.VerifyChangePasswordRequest
import com.app.network.data.responseModels.ChangePasswordResponse
import com.app.network.data.responseModels.GetExchangeRates
import com.app.network.data.responseModels.GetUserProfile
import com.app.network.data.responseModels.VerifyChangePasswordResponse
import com.app.network.retrofitClient.BaseRetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call

class AdjustmentRepository : BaseRetrofitClient(){

   fun getUserInfo(token: String, customerId: String): Call<GetUserProfile> {
        return apiService.getUserInfo(token,customerId)
    }

    fun disable2FA(token: String):Call<ResponseBody>{
        return apiService.disable2FA(token)
    }

    fun getExchangeList(token: String):Call<GetExchangeRates>{
        return apiService.getExchangeList(token)
    }

    fun changePasswordRequest(token: String, changePasswordRequest: ChangePasswordRequest): Call<ChangePasswordResponse> {
        return apiService.changePassword(token, changePasswordRequest)
    }

    fun changePasswordVerify(token: String, verifyChangePasswordRequest: VerifyChangePasswordRequest): Call<VerifyChangePasswordResponse> {
        return apiService.changePasswordVerify(token, verifyChangePasswordRequest)
    }
}