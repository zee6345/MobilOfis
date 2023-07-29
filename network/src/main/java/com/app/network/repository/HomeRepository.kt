package com.app.network.repository

import com.app.network.data.callModels.AccountNickNameRequest
import com.app.network.data.responseModels.GetAccounts
import com.app.network.data.responseModels.GetOldCards
import com.app.network.helper.Keys
import com.app.network.helper.MainApp
import com.app.network.retrofitClient.BaseRetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call

class HomeRepository: BaseRetrofitClient() {

    fun getAccounts(token: String, customerId: Int): Call<GetAccounts> {
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

    fun getAccountBlockByIban(token: String, customerId: Int, iban: String): Call<ResponseBody> {
        return apiService.getAccountBlockByIBAN(customerId,iban)
    }

    fun getOldBusinessCards(token: String, customerId: Int): Call<GetOldCards> {
        return apiService.getOldBusinessCards(token, customerId)
    }

    fun getNewBusinessCards(token: String, customerId: Int): Call<ResponseBody> {
        return apiService.getNewBusinessCards(token, customerId)
    }
}