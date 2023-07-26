package com.app.network.repository

import com.app.network.retrofitClient.BaseRetrofitClient
import okhttp3.ResponseBody

class HomeRepository: BaseRetrofitClient() {

    suspend fun getAccounts(token:String, customerId:Int ):ResponseBody{
        return apiService.getAccounts(token, customerId)
    }

}