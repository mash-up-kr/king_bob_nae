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
import com.example.king_bob_nae.features.intro.presentation.IntroActivity
import com.example.king_bob_nae.features.onboarding.presentation.OnBoardingFirstFragment
import com.example.king_bob_nae.features.onboarding.presentation.OnBoardingLastFragment
import com.example.king_bob_nae.features.onboarding.presentation.OnBoardingSecondFragment

class OnBoardingActivity : BaseActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initStartButton()
        initPager()
    }

    private fun initStartButton() {
        binding.btnOnboardingStart.setOnClickListener {
            Intent(this, IntroActivity::class.java).run {
                startActivity(this)
            }
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
                    when (pager.currentItem) {
                        2 -> {
                            btnOnboardingStart.apply {
                                isClickable = true
                                setBackgroundResource(R.drawable.radius_orange)
                                setTextColor(
                                    ContextCompat.getColor(context, R.color.white)
                                )
                            }
                        }
                        else -> {
                            btnOnboardingStart.apply {
                                isClickable = false
                                setBackgroundResource(R.drawable.radius_gray)
                                setTextColor(
                                    ContextCompat.getColor(context, R.color.brown_gray_300)
                                )
                            }
                        }
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
