package com.app.network.apiService

import com.app.network.data.callModels.AccountNickNameRequest
import com.app.network.data.callModels.ChangePasswordRequest
import com.app.network.data.callModels.LoginAsanRequest
import com.app.network.data.callModels.LoginRequest
import com.app.network.data.callModels.LoginVerificationRequest
import com.app.network.data.callModels.VerifyChangePasswordRequest
import com.app.network.data.responseModels.ChangePasswordResponse
import com.app.network.data.responseModels.GetAccounts
import com.app.network.data.responseModels.GetCustomerBalance
import com.app.network.data.responseModels.GetOldCards
import com.app.network.data.responseModels.LoginAsanResponse
import com.app.network.data.responseModels.LoginResponse
import com.app.network.data.responseModels.LoginVerifyResponse
import com.app.network.data.responseModels.GetNewCards
import com.app.network.data.responseModels.VerifyChangePasswordResponse
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
    suspend fun loginAsan(@Body loginAsanRequest: LoginAsanRequest): LoginAsanResponse

    @POST("auth/changepassword")
    suspend fun changePassword(@Body changePasswordRequest: ChangePasswordRequest): ChangePasswordResponse

    @POST("auth/changepassword/verify")
    suspend fun changePasswordVerify(verifyChangePasswordRequest: VerifyChangePasswordRequest): VerifyChangePasswordResponse

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
    suspend fun getUserInfo(
        @Header("Auth_token") token: String,
        @Query("customerNo") customerNo: String
    ): ResponseBody





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
    ):Call<GetOldCards>


    @GET("brcards/get-new-business-cards-by-cif/{customerId}")
    fun getNewBusinessCards(
        @Header("Auth_token") token: String,
        @Path("customerId") customerId: Int
    ):Call<GetNewCards>


}