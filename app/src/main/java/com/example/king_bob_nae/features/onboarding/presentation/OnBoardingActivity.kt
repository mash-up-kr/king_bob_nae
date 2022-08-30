package com.example.king_bob_nae.features.onboarding.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.king_bob_nae.KkiLogApplication
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseActivity
import com.example.king_bob_nae.databinding.ActivityOnboardingBinding
import com.example.king_bob_nae.features.HomeActivity
import com.example.king_bob_nae.features.intro.presentation.IntroActivity
import com.example.king_bob_nae.utils.isEnabled

class OnBoardingActivity : BaseActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appCheck()
    }

    private fun appCheck() {
        KkiLogApplication.prefs.apply {
            if (!appFirstCheck) {
                initPager()
                initStartButton()
            } else {
                if (isAccessTokenEmpty()) {
                    startActivity(Intent(this@OnBoardingActivity, IntroActivity::class.java))
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    finish()
                } else {
                    startActivity(Intent(this@OnBoardingActivity, HomeActivity::class.java))
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    finish()
                }
            }
        }
    }

    private fun initStartButton() {
        binding.btnOnboardingStart.setOnClickListener {
            KkiLogApplication.prefs.appFirstCheck = true
            Intent(this, IntroActivity::class.java).run {
                startActivity(this)
            }
            finish()
        }
    }

    private fun initPager() {
        binding.apply {
            pager.adapter = PagerAdapter(this@OnBoardingActivity)
            pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            dotsIndicator.setViewPager2(pager)
            pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (binding.pager.currentItem) {
                        2 -> binding.btnOnboardingStart.isEnabled(true)
                        else -> binding.btnOnboardingStart.isEnabled(false)
                    }
                }
            })
        }
    }

    override fun onBackPressed() {
        if (binding.pager.currentItem == 0) super.onBackPressed()
        else binding.pager.currentItem = binding.pager.currentItem - 1
    }

    private class PagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> OnBoardingFirstFragment()
                1 -> OnBoardingSecondFragment()
                else -> OnBoardingLastFragment()
            }
        }

    }
}