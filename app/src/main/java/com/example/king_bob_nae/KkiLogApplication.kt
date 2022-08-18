package com.example.king_bob_nae

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KkiLogApplication : Application() {
    companion object {
        lateinit var prefs: SharedPreferences
    }

    override fun onCreate() {
        prefs = SharedPreferences
        prefs.initialize(this)
        super.onCreate()
    }
}
