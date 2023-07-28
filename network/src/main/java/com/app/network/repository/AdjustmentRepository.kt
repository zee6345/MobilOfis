package com.app.network.repository

import com.app.network.retrofitClient.BaseRetrofitClient
import okhttp3.ResponseBody

class AdjustmentRepository : BaseRetrofitClient(){
  suspend  fun getUserInfo(token: String, customerId: String):ResponseBody {
        return apiService.getUserInfo(token,customerId)
    }
}