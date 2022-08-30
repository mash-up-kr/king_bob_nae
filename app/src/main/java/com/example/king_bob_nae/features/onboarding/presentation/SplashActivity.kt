package com.example.king_bob_nae.features.onboarding.presentation

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseActivity
import com.example.king_bob_nae.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val DELAY_TIME = 1500L

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            delay(DELAY_TIME)
            startActivity(Intent(this@SplashActivity, OnBoardingActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }
    }
}