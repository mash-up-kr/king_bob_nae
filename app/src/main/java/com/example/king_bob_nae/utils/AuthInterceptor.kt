package com.example.king_bob_nae.utils

import com.example.king_bob_nae.KkiLogApplication
import okhttp3.Interceptor

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain) =
        chain.proceed(
            chain.request().newBuilder().apply {
                KkiLogApplication.prefs.accessToken?.run {
                    header("Authorization", "Bearer $this")
                }
            }.build()
        )
}