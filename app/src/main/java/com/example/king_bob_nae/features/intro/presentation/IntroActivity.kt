package com.example.king_bob_nae.features.intro.presentation

import android.os.Bundle
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseActivity
import com.example.king_bob_nae.databinding.ActivitySigninBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroActivity : BaseActivity<ActivitySigninBinding>(R.layout.activity_signin) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
