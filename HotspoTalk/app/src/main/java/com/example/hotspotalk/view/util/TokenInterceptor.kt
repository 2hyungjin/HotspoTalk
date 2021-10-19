package com.example.hotspotalk.view.util

import com.example.hotspotalk.view.util.Preference.token
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request().newBuilder().addHeader("authorization", token).build()
        return chain.proceed(req)
    }

}