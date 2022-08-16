package com.example.king_bob_nae

import android.content.Context
import android.content.SharedPreferences

object SharedPreferences {
    private const val KKILOG_PREFS = "kkilog_prefs"
    private const val ACCESS_TOKEN_KEY = "kkilog_access_token"
    private const val ONBOARDING_KEY = "kkilog_onboarding"

    private lateinit var prefs: SharedPreferences

    fun initialize(context: Context) {
        prefs = context.getSharedPreferences(KKILOG_PREFS, Context.MODE_PRIVATE)
    }

    var accessToken: String?
        get() = prefs.getString(ACCESS_TOKEN_KEY, null)
        set(value) = prefs.edit().putString(ACCESS_TOKEN_KEY, value).apply()

    fun clearAccessToken() {
        prefs.edit().apply {
            remove(ACCESS_TOKEN_KEY)
            apply()
        }
    }
}