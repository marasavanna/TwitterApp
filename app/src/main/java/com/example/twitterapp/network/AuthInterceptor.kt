package com.example.twitterapp.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer AAAAAAAAAAAAAAAAAAAAABI7OAEAAAAAQHe3Ii7C%2FvXDC49RL72YsaCy5Pg%3D1E2p4rF8Glk4BjXTEj4k8M1HEpQVUYugcbXgbOH8sktmOoptuz")
        return chain.proceed(requestBuilder.build())
    }
}