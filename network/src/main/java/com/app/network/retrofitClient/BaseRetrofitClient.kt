package com.app.network.retrofitClient


import android.util.Log
import com.app.network.apiService.APIService
import com.app.network.helper.Keys
import com.app.network.helper.MainApp
import com.app.network.retrofitClient.BaseRetrofitClient.Companion.AUTH_TOKEN
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

private const val baseUrl = "https://newinternetofistest.bankrespublika.az/weblogic/eGlobeService/rest/"

abstract class BaseRetrofitClient {

    protected val apiService: APIService by lazy { createApiService() }

    companion object {
        var AUTH_TOKEN:String ? = null

        // Create a single instance of OkHttpClient for all services
        private val httpClient by lazy {
            OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
//                .addInterceptor(AuthInterceptor(AUTH_TOKEN))
                .addInterceptor(AuthTokenInterceptor())
                .build()
        }

    }

    private fun client(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createApiService(): APIService {
        return client(httpClient).create(APIService::class.java)
    }

}

class AuthInterceptor(authToken: String?) : Interceptor {
    private val token = authToken ?: MainApp.session[Keys.KEY_TOKEN]!!

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Auth_token", token)
            .build()
        return chain.proceed(request)
    }
}

class AuthTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = try {
            chain.proceed(request)
        } catch (e: IOException) {
            // Handle network exceptions here if needed
//            throw NetworkException("Network error: ${e.message}")
            createDefaultResponse()
        }

        val authToken = response.header("Auth_token")
        authToken?.let {
            // Store auth token
            MainApp.session.put(Keys.KEY_TOKEN, it)
            AUTH_TOKEN = it
        }

        return response
    }
}

private fun createDefaultResponse(): Response {
    return Response.Builder()
        .code(500) // You can choose an appropriate error code here
        .protocol(Protocol.HTTP_1_1)
        .message("Network error")
        .body(ResponseBody.create("text/plain".toMediaTypeOrNull(), ""))
        .build()
}

// Custom network exception class
class NetworkException(message: String) : IOException(message)
