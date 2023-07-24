package com.app.network.retrofitClient

import com.app.network.apiService.APIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl = "https://newinternetofistest.bankrespublika.az/weblogic/eGlobeService/rest/"
abstract class BaseRetrofitClient {


    protected val apiService: APIService by lazy { createApiService() }

    private fun createApiService(): APIService {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(APIService::class.java)
    }
}