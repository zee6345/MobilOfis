package com.app.network.repository

import com.app.network.models.requestModels.AccountNickNameRequest
import com.app.network.models.requestModels.ChangeCompanyName
import com.app.network.models.requestModels.GetPdfList
import com.app.network.models.requestModels.SendToBankModel
import com.app.network.models.requestModels.SignApproveRequest
import com.app.network.models.responseModels.GetAccountBlocks
import com.app.network.models.responseModels.GetAccounts
import com.app.network.models.responseModels.GetCustomerBalance
import com.app.network.models.responseModels.GetLoans
import com.app.network.models.responseModels.GetNewCards
import com.app.network.models.responseModels.GetOldCards
import com.app.network.models.responseModels.GetPdfResponse
import com.app.network.models.responseModels.GetRecentOps
import com.app.network.models.responseModels.GetRecentOpsItem
import com.app.network.models.responseModels.GetTransactionDetails
import com.app.network.models.responseModels.GetTrusts
import com.app.network.models.responseModels.GetUserRoles
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.models.responseModels.SignApproveResponse
import com.app.network.models.responseModels.TransactionDetails
import com.app.network.models.responseModels.transferModels.TransferCountSummaryResponse
import com.app.network.models.responseModels.transferModels.TransferListResponse
import com.app.network.retrofitClient.APIService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiService: APIService) {

    fun getAccounts(token: String, customerId: Int): Call<GetAccounts> {
        return apiService.getAccounts(token, customerId)
    }

//    suspend fun getLastLogin(token:String):Response<String>{
//        return apiService.getLastLogin(token)
//    }

    fun getUserBalance(token: String, customerId: Int): Call<GetCustomerBalance> {
        return apiService.getBalance(token, customerId)
    }

    suspend fun setUserNickName(
        token: String,
        accountNickNameRequest: AccountNickNameRequest
    ): ResponseBody {
        return apiService.setAccountNickName(token, accountNickNameRequest)
    }

    fun getAccountBlockByIban(
        token: String,
        customerId: Int,
        iban: String
    ): Call<GetAccountBlocks> {
        return apiService.getAccountBlockByIBAN(token, customerId, iban)
    }

    fun getOldBusinessCards(token: String, customerId: Int): Call<GetOldCards> {
        return apiService.getOldBusinessCards(token, customerId)
    }

    fun getNewBusinessCards(token: String, customerId: Int): Call<GetNewCards> {
        return apiService.getNewBusinessCards(token, customerId)
    }

    fun getLoans(token: String, customerId: Int): Call<GetLoans> {
        return apiService.getLoans(token, customerId)
    }

    fun getTrusts(token: String, customerId: Int): Call<GetTrusts> {
        return apiService.getTrusts(token, customerId)
    }

    fun getRecentOps(token: String, customerId: Int): Call<GetRecentOps> {
        return apiService.getRecentOps(token, customerId)
    }

    fun getRecentOps(token: String, customerId: Int, incomeFlag: String): Call<GetRecentOps> {
        return apiService.getRecentOps(token, customerId, incomeFlag)
    }

//    suspend fun getRecentOps(
//        token: String,
//        customerId: Int,
//        page: Int,
//        incomeFlag: String = ""
//    ): Response<List<GetRecentOpsItem>> {
//        return apiService.getRecentOps(
//            token = token,
//            customerId = customerId,
//            page = page,
//            flag = incomeFlag
//        )
//    }

    fun setCustomerName(
        token: String,
        changeCompanyName: ChangeCompanyName
    ): Call<LoginVerifyResponse> {
        return apiService.setCompanyName(token, changeCompanyName)
    }

    fun getBusinessDate(token: String): Call<ResponseBody> {
        return apiService.getBusinessDate(token)
    }


    fun getTransferCountSummary(
        token: String,
        startDate: String,
        endDate: String
    ): Call<TransferCountSummaryResponse> {
        return apiService.getTransferCountSummary(token, startDate, endDate)
    }

    fun getTransferList(
        token: String,
        startDate: String,
        endDate: String,
        page: Int
    ): Call<TransferListResponse> {
        return apiService.getTransferList(token, startDate, endDate, page)
    }

    fun getTransferPdfList(token: String, getPdfList: GetPdfList):Call<GetPdfResponse>{
        return apiService.getTransferPdfList(token, getPdfList)
    }

    fun userRoles(token: String): Call<GetUserRoles> {
        return apiService.userRoles(token)
    }

    fun getTransactionDetails(token: String, ibankRef: String): Call<TransactionDetails> {
        return apiService.transactionDetails(token, ibankRef)
    }

    fun signOrApprove(
        token: String,
        signApproveRequest: SignApproveRequest
    ): Call<SignApproveResponse> {
        return apiService.signOrApprove(token, signApproveRequest)
    }

    fun transactionStatus(token: String, code: Int): Call<SignApproveResponse> {
        return apiService.transactionStatus(token, code)
    }

    fun sendToBankAPI(token: String, sendToBankModel: SendToBankModel): Call<SignApproveResponse> {
        return apiService.sendToBankAPI(token, sendToBankModel)
    }

}