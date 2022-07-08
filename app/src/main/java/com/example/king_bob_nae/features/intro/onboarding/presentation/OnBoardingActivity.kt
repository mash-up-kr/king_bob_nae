package com.example.king_bob_nae.features.intro.onboarding.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseActivity
import com.example.king_bob_nae.databinding.ActivityOnboardingBinding

class OnBoardingActivity : BaseActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initPager()
    }

    private fun initPager() {
        binding.apply {
            pager.adapter = PagerAdapter(this@OnBoardingActivity)
            pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            dotsIndicator.setViewPager2(pager)
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