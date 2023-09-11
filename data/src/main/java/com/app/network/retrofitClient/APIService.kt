package com.app.network.retrofitClient

import com.app.network.models.requestModels.AccountNickNameRequest
import com.app.network.models.requestModels.ChangeCompanyName
import com.app.network.models.requestModels.ChangePasswordRequest
import com.app.network.models.requestModels.GetPdfList
import com.app.network.models.requestModels.LoginAsanRequest
import com.app.network.models.requestModels.LoginRequest
import com.app.network.models.requestModels.LoginVerificationRequest
import com.app.network.models.requestModels.SendToBankModel
import com.app.network.models.requestModels.SetFavCustomer
import com.app.network.models.requestModels.SignApproveRequest
import com.app.network.models.requestModels.VerifyChangePasswordRequest
import com.app.network.models.requestModels.VerifyRequest
import com.app.network.models.requestModels.VerifySecretRequest
import com.app.network.models.responseModels.ChangePasswordResponse
import com.app.network.models.responseModels.GetAccountBlocks
import com.app.network.models.responseModels.GetAccounts
import com.app.network.models.responseModels.GetCustomerBalance
import com.app.network.models.responseModels.GetExchangeRates
import com.app.network.models.responseModels.GetLastLogin
import com.app.network.models.responseModels.GetLoans
import com.app.network.models.responseModels.GetNewCards
import com.app.network.models.responseModels.GetOldCards
import com.app.network.models.responseModels.GetPdfResponse
import com.app.network.models.responseModels.GetRecentOps
import com.app.network.models.responseModels.GetRecentOpsItem
import com.app.network.models.responseModels.GetStartMessage
import com.app.network.models.responseModels.GetTransactionDetails
import com.app.network.models.responseModels.GetTrusts
import com.app.network.models.responseModels.GetUserProfile
import com.app.network.models.responseModels.GetUserRoles
import com.app.network.models.responseModels.GetVerify2FA
import com.app.network.models.responseModels.LoginAsanResponse
import com.app.network.models.responseModels.LoginResponse
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.models.responseModels.SignApproveResponse
import com.app.network.models.responseModels.TransactionDetails
import com.app.network.models.responseModels.VerifyChangePasswordResponse
import com.app.network.models.responseModels.transferModels.TransferCountSummaryResponse
import com.app.network.models.responseModels.transferModels.TransferListResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface APIService {

    // Define your API endpoints here
    @POST("auth/login")
    fun loginWithUserName(@Body loginBody: LoginRequest): Call<LoginResponse>

    @POST("auth/login/verify")
    fun loginVerification(
        @Header("Auth_token") token: String,
        @Body loginVerification: LoginVerificationRequest
    ): Call<LoginVerifyResponse>

    @POST("auth/asan")
    fun loginAsan(@Body loginAsanRequest: LoginAsanRequest): Call<LoginAsanResponse>

    @POST("auth/changepassword")
    fun changePassword(
        @Header("Auth_token") token: String,
        @Body changePasswordRequest: ChangePasswordRequest
    ): Call<ChangePasswordResponse>

    @POST("auth/changepassword/verify")
    fun changePasswordVerify(
        @Header("Auth_token") token: String,
        @Body verifyChangePasswordRequest: VerifyChangePasswordRequest
    ): Call<VerifyChangePasswordResponse>


    @POST("auth/set-fav-customer")
    fun setFavCustomer(
        @Header("Auth_token") token: String,
        @Body setFavCustomer: SetFavCustomer
    ): Call<ResponseBody>

    // Main
    @GET("customers/{customerId}/accounts")
    fun getAccounts(
        @Header("Auth_token") token: String,
        @Path("customerId") customerId: Int
    ): Call<GetAccounts>

    @GET("customers/{customerId}/accounts/nickname")
    suspend fun setAccountNickName(
        @Header("Auth_token") token: String,
        @Body accountNickNameRequest: AccountNickNameRequest
    ): ResponseBody


    @GET("customers/{customerId}/balance")
    fun getBalance(
        @Header("Auth_token") token: String,
        @Path("customerId") customerId: Int
    ): Call<GetCustomerBalance>


    @GET("auth/getlastlogin")
    fun getLastLogin(
        @Header("Auth_token") token: String
    ): Call<GetLastLogin>


    @GET("auth/getuserinfo")
    fun getUserInfo(
        @Header("Auth_token") token: String,
        @Query("customerNo") customerNo: String
    ): Call<GetUserProfile>


    @GET("customers/{customerId}/accounts/blocksbyiban/{iban}")
    fun getAccountBlockByIBAN(
        @Header("Auth_token") token: String,
        @Path("customerId") customerId: Int,
        @Path("iban") iban: String
    ): Call<GetAccountBlocks>


    @GET("brcards/get-old-business-cards-by-cif/{customerId}")
    fun getOldBusinessCards(
        @Header("Auth_token") token: String,
        @Path("customerId") customerId: Int
    ): Call<GetOldCards>


    @GET("brcards/get-new-business-cards-by-cif/{customerId}")
    fun getNewBusinessCards(
        @Header("Auth_token") token: String,
        @Path("customerId") customerId: Int
    ): Call<GetNewCards>


    @GET("credit/{customerId}")
    fun getLoans(
        @Header("Auth_token") token: String,
        @Path("customerId") customerId: Int
    ): Call<GetLoans>


    @GET("customers/{customerId}/timedeposits")
    fun getTrusts(
        @Header("Auth_token") token: String,
        @Path("customerId") customerId: Int
    ): Call<GetTrusts>


    @GET("customers/{customerId}/activities-byfilter?page=1&limit=20")
    fun getRecentOps(
        @Header("Auth_token") token: String,
        @Path("customerId") customerId: Int,
        @Query("debit_credit_flag") flag: String
    ): Call<GetRecentOps>

    @GET("customers/{customerId}/activities-byfilter?page=1&limit=20")
    fun getRecentOps(
        @Header("Auth_token") token: String,
        @Path("customerId") customerId: Int
    ): Call<GetRecentOps>

    @POST("auth/totp-register/disable")
    fun disable2FA(
        @Header("Auth_token") token: String,
    ): Call<ResponseBody>

    @POST("auth/totp-register/enable")
    fun enable2FA(
        @Header("Auth_token") token: String,
    ): Call<ResponseBody>

    @POST("auth/totp-register/status")
    fun verify2FA(
        @Header("Auth_token") token: String,
        @Body verify2FARequest: VerifyRequest
    ): Call<GetVerify2FA>


    @POST("auth/totp-register/verify")
    fun verify2FASecret(
        @Header("Auth_token") token: String,
        @Body verifySecretRequest: VerifySecretRequest
    ): Call<GetVerify2FA>

    @GET("exchange/exchange/listrates")
    fun getExchangeList(
        @Header("Auth_token") token: String
    ): Call<GetExchangeRates>

    @POST("auth/setcustomer")
    fun setCompanyName(
        @Header("Auth_token") token: String,
        @Body chaneCompanyName: ChangeCompanyName
    ): Call<LoginVerifyResponse>

    //transfers
    @GET("bank/bussinessdate")
    fun getBusinessDate(
        @Header("Auth_token") token: String
    ): Call<ResponseBody>

    @GET("transaction/transfer-count-summary")
    fun getTransferCountSummary(
        @Header("Auth_token") token: String,
        @Query("startdate") startDate: String,
        @Query("enddate") endDate: String
    ): Call<TransferCountSummaryResponse> // transfer count summary response

    @GET("transaction/transfer-list/{startDate}/{endDate}")
    fun getTransferList(
        @Header("Auth_token") token: String,
        @Path("startDate") startDate: String,
        @Path("endDate") endDate: String,
        @Query("page") page: Int
    ): Call<TransferListResponse> // transfer list response

    @POST("file/get-list-payment-order-pdf")
    fun getTransferPdfList(
        @Header("Auth_token") token: String,
        @Body getPdfList: GetPdfList
    ): Call<GetPdfResponse>

    @GET("transaction/getransactionbyibankref/{ibankRef}")
    fun transactionDetails(
        @Header("Auth_token") token: String,
        @Path("ibankRef") ibankRef: String
//    ): Call<GetTransactionDetails>
    ): Call<TransactionDetails>

    @POST("auth/rule-transaction-button")
    fun userRoles(
        @Header("Auth_token") token: String,
    ):Call<GetUserRoles>

    @POST("transaction/approve")
    fun signOrApprove(
        @Header("Auth_token") token: String,
        @Body signApproveRequest: SignApproveRequest
    ): Call<SignApproveResponse>

    @GET("transaction/status/{code}")
    fun transactionStatus(
        @Header("Auth_token") token: String,
        @Path("code") code: Int
    ): Call<SignApproveResponse>

    @POST("transaction/send-to-bank")
    fun sendToBankAPI(
        @Header("Auth_token") token: String,
        @Body sendToBank: SendToBankModel
    ): Call<SignApproveResponse>

    @GET("bank/dashboardMessage")
    fun getDashBoardMessage(): Call<GetStartMessage>

}