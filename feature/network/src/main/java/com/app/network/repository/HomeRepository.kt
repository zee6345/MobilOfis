package com.app.network.repository

import com.app.network.data.callModels.AccountNickNameRequest
import com.app.network.retrofitClient.BaseRetrofitClient
import okhttp3.ResponseBody

class HomeRepository: BaseRetrofitClient() {

    suspend fun getAccounts(token:String, customerId:Int ):ResponseBody{
        return apiService.getAccounts(token, customerId)
    }

    suspend fun getLastLogin(token:String):ResponseBody{
        return apiService.getLastLogin(token)
    }

    suspend fun getUserInfo(token: String, customerNo: String):ResponseBody {
        return apiService.getUserInfo(token,customerNo)
    }

   suspend fun getUserBalance(token: String, customerId: String):ResponseBody {
        return apiService.getBalance(token,customerId)
    }

    suspend fun setUserNickName(token: String, accountNickNameRequest: AccountNickNameRequest):ResponseBody{
        return apiService.setAccountNickName(token, accountNickNameRequest)
    }

    suspend fun getAccountBlockByIban(token: String, customerId: String, iban: String):ResponseBody {
        return apiService.getAccountBlockByIBAN(token,customerId,iban)
    }
}