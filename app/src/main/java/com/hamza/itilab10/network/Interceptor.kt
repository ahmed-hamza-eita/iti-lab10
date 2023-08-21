package com.hamza.itilab10.network

import okhttp3.Interceptor
import okhttp3.Response

class CustomInterceptor(private val headerName: String, private val headerValue: String) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
            .addHeader(headerName, headerValue)
            .build()
        return chain.proceed(requestBuilder)
    }
}