package com.app.network.retrofitClient


import com.app.network.apiService.APIService
import com.app.network.helper.MainApp

import com.app.network.retrofitClient.BaseRetrofitClient.Companion.AUTH_TOKEN
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl = "https://newinternetofistest.bankrespublika.az/weblogic/eGlobeService/rest/"

abstract class BaseRetrofitClient {
    companion object {
         var AUTH_TOKEN = "testing"
    }
    protected val apiService: APIService by lazy { createApiService() }
    protected val apiServiceAuthInterceptor by lazy { createApiServiceAuthIntercepter() }

    private fun createApiService(): APIService {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(AuthInterceptor(AUTH_TOKEN))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(APIService::class.java)
    }


    private fun createApiServiceAuthIntercepter(): APIService {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(AuthTokenInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(APIService::class.java)
    }


}



class AuthInterceptor(private val authToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Auth_token", authToken)
            .build()

        return chain.proceed(request)
    }
}

class AuthTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        // Extract the AUTH_TOKEN from the response headers
        val authToken = response.header("Auth_token")

        // You can now store the token in a shared preference or any other place for future use
        // For this example, I'm just printing it
        authToken?.let {
            println("auth_token: $it")
            //store auth token
            MainApp.session.put("token", it)

            AUTH_TOKEN= it
        }

        return response
    }
}