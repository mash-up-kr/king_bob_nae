package com.example.king_bob_nae.features.home.tutorial

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentTutorialSecondBinding

class SecondTutorialFragment :
    BaseFragment<FragmentTutorialSecondBinding>(R.layout.fragment_tutorial_second) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.btnTipFollowNext.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_secondTutorialFragment_to_lastTutorialFragment)
        }
    }
}