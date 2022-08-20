package com.example.king_bob_nae.features.myprofile.presentation.follow

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentFollowingBinding

class FollowingFragment : BaseFragment<FragmentFollowingBinding>(R.layout.fragment_following) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.btnFollowingBack.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }
}