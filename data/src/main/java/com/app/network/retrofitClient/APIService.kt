package com.app.network.retrofitClient

import com.app.network.models.requestModels.AccountNickNameRequest
import com.app.network.models.requestModels.ChangeCompanyName
import com.app.network.models.requestModels.ChangePasswordRequest
import com.app.network.models.requestModels.LoginAsanRequest
import com.app.network.models.requestModels.LoginRequest
import com.app.network.models.requestModels.LoginVerificationRequest
import com.app.network.models.requestModels.VerifyChangePasswordRequest
import com.app.network.models.responseModels.ChangePasswordResponse
import com.app.network.models.responseModels.GetAccounts
import com.app.network.models.responseModels.GetCustomerBalance
import com.app.network.models.responseModels.GetExchangeRates
import com.app.network.models.responseModels.GetLoans
import com.app.network.models.responseModels.GetOldCards
import com.app.network.models.responseModels.LoginAsanResponse
import com.app.network.models.responseModels.LoginResponse
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.models.responseModels.GetNewCards
import com.app.network.models.responseModels.GetRecentOps
import com.app.network.models.responseModels.GetTrusts
import com.app.network.models.responseModels.GetUserProfile
import com.app.network.models.responseModels.VerifyChangePasswordResponse
import okhttp3.ResponseBody
import retrofit2.Call
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
    suspend fun getLastLogin(@Header("auth_token") token: String): ResponseBody


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
    ): Call<ResponseBody>


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
        @Path("customerId") customerId: Int
    ): Call<GetRecentOps>

    @POST("auth/totp-register/disable")
    fun disable2FA(
        @Header("Auth_token") token: String,
    ): Call<ResponseBody>

    @GET("exchange/exchange/listrates")
    fun getExchangeList(
        @Header("Auth_token") token:String
    ):Call<GetExchangeRates>

    @POST("auth/setcustomer")
    fun setCompanyName(
        @Header("Auth_token") token:String,
        @Body chaneCompanyName: ChangeCompanyName
    ):Call<LoginVerifyResponse>

    //transfers
    @GET("bank/bussinessdate")
    fun getBusinessDate(
        @Header("Auth_token") token:String
    ):Call<ResponseBody>

    @GET("bank/Accounts")
    fun getAccounts(
        @Header("Auth_token") token:String
    ):Call<ResponseBody>

    @GET("bank/transfer-count-summary")
    fun getTransferCountSummary(
        @Header("Auth_token") token:String
    ):Call<ResponseBody>

    @GET("bank/transfer-list")
    fun getTransferList(
        @Header("Auth_token") token:String
    ):Call<ResponseBody>

}