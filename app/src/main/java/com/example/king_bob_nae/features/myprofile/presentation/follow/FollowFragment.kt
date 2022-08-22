package com.example.king_bob_nae.features.myprofile.presentation.follow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentFollowBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch

private const val NUM_TABS = 2

class FollowFragment : BaseFragment<FragmentFollowBinding>(R.layout.fragment_follow) {
    private val safeArgs by navArgs<FollowFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {
            vpFollow.adapter = ViewPagerAdapter(this@FollowFragment)

            TabLayoutMediator(binding.tabLayout, binding.vpFollow) { tab, position ->
                tab.text = resources.getStringArray(R.array.my_profile_follow_list)[position]
            }.attach()

            btnAddFollower.setOnClickListener {
                it.findNavController().navigate(R.id.action_followFragment_to_followingFragment)
            }

            btnFollowBack.setOnClickListener {
                it.findNavController().popBackStack()
            }
            viewLifecycleOwner.lifecycleScope.launch {
                vpFollow.currentItem = safeArgs.label
            }
        }
    }
}

class ViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = NUM_TABS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyFollowerFragment()
            else -> MyFollowingFragment()
        }
    }

}
