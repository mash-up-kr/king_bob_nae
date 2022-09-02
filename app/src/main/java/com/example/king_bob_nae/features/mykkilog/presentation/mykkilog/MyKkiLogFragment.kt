package com.example.king_bob_nae.features.mykkilog.presentation.mykkilog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentMyKkilogBinding
import com.google.android.material.tabs.TabLayoutMediator

private const val NUM_TABS = 2

class MyKkiLogFragment : BaseFragment<FragmentMyKkilogBinding>(R.layout.fragment_my_kkilog) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.vpMyKkilog.adapter = ViewPagerAdapter(this@MyKkiLogFragment)
        TabLayoutMediator(binding.tlMyKkilog, binding.vpMyKkilog) { tab, position ->
            tab.text = resources.getStringArray(R.array.my_kkilog_list)[position]
        }.attach()
    }
}

class ViewPagerAdapter(val fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = NUM_TABS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MySimpleKkiLogFragment()
            else -> MyDetailKkiLogFragment()
        }
    }

}
