package com.example.king_bob_nae.features.myprofile.presentation.main

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentMyprofileMainBinding

class MyProfileMainFragment :
    BaseFragment<FragmentMyprofileMainBinding>(R.layout.fragment_myprofile_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView() {
        binding.apply {
            tvMyProfileTotalFollowerCount.setOnClickListener {
                it.findNavController().navigate(R.id.action_myProfileMainFragment_to_followFragment)
            }
            tvMyProfileFollower.setOnClickListener {
                it.findNavController().navigate(R.id.action_myProfileMainFragment_to_followFragment)
            }
            tvMyProfileFollowing.setOnClickListener {
                it.findNavController().navigate(R.id.action_myProfileMainFragment_to_followFragment)
            }
            tvMyProfileTotalFollowingCount.setOnClickListener {
                it.findNavController().navigate(R.id.action_myProfileMainFragment_to_followFragment)
            }
        }
    }
}
