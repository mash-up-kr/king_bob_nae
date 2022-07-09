package com.example.king_bob_nae.features.intro.onboarding.presentation

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseActivity
import com.example.king_bob_nae.databinding.ActivityOnboardingBinding
import com.example.king_bob_nae.features.intro.IntroActivity

class OnBoardingActivity : BaseActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnOnboardingStart.setOnClickListener {
            Intent(this, IntroActivity::class.java).run {
                startActivity(this)
            }
        }
        initPager()
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
                        2 -> {
                            binding.btnOnboardingStart.apply{
                                isClickable = true
                                setBackgroundResource(R.drawable.radius_orange)
                                setTextColor(
                                    ContextCompat.getColor(context,R.color.white)
                                )
                            }}
                        else -> {
                            binding.btnOnboardingStart.apply{
                                isClickable = false
                                setBackgroundResource(R.drawable.radius_gray)
                                setTextColor(
                                    ContextCompat.getColor(context,R.color.brown_gray_300)
                                )
                            }}
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