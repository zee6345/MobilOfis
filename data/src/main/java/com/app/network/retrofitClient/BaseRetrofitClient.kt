package com.app.network.retrofitClient


import com.app.network.helper.Keys
import com.app.network.helper.Session
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val baseUrl =
    "https://newinternetofistest.bankrespublika.az/weblogic/eGlobeService/rest/"

class BaseRetrofitClient @Inject constructor(private val session: Session) {

    var AUTH_TOKEN: String? = null

    fun createApiService(): APIService {
        return client(httpClient).create(APIService::class.java)
    }

    private fun client(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(authIntercept)
            .build()
    }

    private val authIntercept = Interceptor { chain ->
        val request = chain.request()
        var response: Response?

        try {
            response = chain.proceed(request)
            val authToken = response.header("Auth_token")
            authToken?.let {
                // Store auth token
                session.put(Keys.KEY_TOKEN, it)
                AUTH_TOKEN = it
            }
        } catch (e: Exception) {
            response = chain.proceed(request)
        }

        response!!
    }

    private fun createDefaultResponse(): Response {
        return Response.Builder()
            .code(500) // Set appropriate HTTP status code
            .protocol(Protocol.HTTP_1_1) // Set the protocol
            .body("".toResponseBody("text/plain".toMediaTypeOrNull()))
            .request(Request.Builder().url(baseUrl).build()) // Set a dummy request
            .build()
    }

}