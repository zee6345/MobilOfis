package com.app.network.retrofitClient


import android.content.Context
import com.app.network.apiService.APIService
import com.app.network.helper.Keys
import com.app.network.helper.MainApp
import com.app.network.helper.Session
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
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
import javax.inject.Inject

private const val baseUrl =
    "https://newinternetofistest.bankrespublika.az/weblogic/eGlobeService/rest/"

class BaseRetrofitClient @Inject constructor(private val session: Session) {

    var AUTH_TOKEN: String? = null

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
//                .addInterceptor(AuthInterceptor(AUTH_TOKEN))
            .addInterceptor(authIntercept)
            .build()
    }


    private fun client(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createApiService(): APIService {
        return client(httpClient).create(APIService::class.java)
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
        } catch (e: IOException) {
            response = createDefaultResponse()
        }

        response!!
    }

    private fun createDefaultResponse(): Response {
        return Response.Builder()
            .code(500) // Set appropriate HTTP status code
            .protocol(Protocol.HTTP_1_1) // Set the protocol
            .body(ResponseBody.create("text/plain".toMediaTypeOrNull(), ""))
            .request(Request.Builder().url(baseUrl).build()) // Set a dummy request
            .build()
    }

}

//class AuthInterceptor(authToken: String?) : Interceptor {
//    private val token = authToken ?: MainApp.session[Keys.KEY_TOKEN]!!
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request().newBuilder()
//            .addHeader("Auth_token", token)
//            .build()
//        return chain.proceed(request)
//    }
//}

//class AuthTokenInterceptor : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request()
//        var response:Response ?= null
//
//        if (request != null) {
//            response = try {
//                chain.proceed(request)
//            } catch (e: IOException) {
//                // Handle network exceptions here if needed
////            throw NetworkException("Network error: ${e.message}")
//                createDefaultResponse()
//            }
//
//
//            val authToken = response!!.header("Auth_token")
//            authToken?.let {
//                // Store auth token
//                MainApp.session.put(Keys.KEY_TOKEN, it)
//                AUTH_TOKEN = it
//            }
//        }
//
//        return response!!
//    }
//}


//class AuthTokenInterceptor @Inject constructor(private val session: Session) : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request()
//        var response: Response?
//
//        try {
//            response = chain.proceed(request)
//
//            val authToken = response.header("Auth_token")
//            authToken?.let {
//                // Store auth token
//                session.put(Keys.KEY_TOKEN, it)
//                BaseRetrofitClient().AUTH_TOKEN = it
//            }
//        } catch (e: IOException) {
//            // Handle network exceptions here if needed
//            // You can throw a custom NetworkException here if desired
//            // throw NetworkException("Network error: ${e.message}")
//            response = createDefaultResponse()
//        }
//
//        return response!!
//    }
//
//    // Define the createDefaultResponse function to return a default Response
//    private fun createDefaultResponse(): Response {
//        // Create and return a default Response here
//        // For example:
//        return Response.Builder()
//            .code(500) // Set appropriate HTTP status code
//            .protocol(Protocol.HTTP_1_1) // Set the protocol
//            .body(ResponseBody.create("text/plain".toMediaTypeOrNull(), ""))
//            .request(Request.Builder().url(baseUrl).build()) // Set a dummy request
//            .build()
//    }
//}


//private fun createDefaultResponse(): Response {
//    return Response.Builder()
//        .code(500) // You can choose an appropriate error code here
//        .protocol(Protocol.HTTP_1_1)
//        .message("Network error")
//        .body(ResponseBody.create("text/plain".toMediaTypeOrNull(), ""))
//        .build()
//}

//// Custom network exception class
//class NetworkException(message: String) : IOException(message)
