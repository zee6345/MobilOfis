package com.app.network.helper

import okhttp3.Interceptor
import okhttp3.Response

class CustomInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // Perform any required actions before making the API request
        // For example, adding headers or logging request details

        val request = chain.request().newBuilder()
            // Add headers if needed
            //.addHeader("Authorization", "Bearer YOUR_ACCESS_TOKEN")
            .build()

        return chain.proceed(request)
    }
}