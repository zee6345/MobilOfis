package com.app.network.repository

import com.app.network.data.responseModels.GetUserProfile
import com.app.network.retrofitClient.BaseRetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call

class AdjustmentRepository : BaseRetrofitClient(){

   fun getUserInfo(token: String, customerId: String): Call<GetUserProfile> {
        return apiService.getUserInfo(token,customerId)
    }
}