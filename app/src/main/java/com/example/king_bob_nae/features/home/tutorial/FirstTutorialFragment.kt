package com.example.king_bob_nae.features.home.tutorial

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentTutorialFirstBinding

class FirstTutorialFragment :
    BaseFragment<FragmentTutorialFirstBinding>(R.layout.fragment_tutorial_first) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.btnTipKkilogNext.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_firstTutorialFragment_to_secondTutorialFragment)
        }
    }
}