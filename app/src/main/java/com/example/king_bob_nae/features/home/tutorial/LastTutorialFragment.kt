package com.example.king_bob_nae.features.home.tutorial

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.king_bob_nae.KkiLogApplication
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentTutorialLastBinding

class LastTutorialFragment :
    BaseFragment<FragmentTutorialLastBinding>(R.layout.fragment_tutorial_last) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.btnTipGrowNext.setOnClickListener {
            it.findNavController().navigate(R.id.action_lastTutorialFragment_to_homeFragment)
            KkiLogApplication.prefs.coachMarkCheck = true
        }
    }
}