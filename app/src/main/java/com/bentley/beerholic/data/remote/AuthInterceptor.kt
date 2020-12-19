package com.bentley.beerholic.data.remote

import com.bentley.beerholic.utils.NetworkCheck
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class AuthInterceptor constructor(private val networkCheck: NetworkCheck) : Interceptor {

    init {
        networkCheck.registerNetworkCallback()
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader(AUTHORIZATION, REST_API_KEY)
        requestBuilder.header("UserBody-Agent", "android")
        requestBuilder.addHeader("Content-Type", "application/json")

        Timber.d("Network Status: ${networkCheck.isConnected()}")
        if (networkCheck.isConnected()) {
            requestBuilder.header("Cache-Control", "public, max-age=" + 60).build()
        } else {
            requestBuilder.header(
                "Cache-Control",
                "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
            ).build()
        }

        return chain.proceed(requestBuilder.build())
    }

    companion object {
        const val AUTHORIZATION = "Authorization"
        const val REST_API_KEY = "KakaoAK d96b7b300b1e64fbdb3699c7da2b4d8a"
    }
}
