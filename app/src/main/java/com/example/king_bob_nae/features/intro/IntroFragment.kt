package com.example.king_bob_nae.features.intro

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentIntroBinding

class IntroFragment : BaseFragment<FragmentIntroBinding>(R.layout.fragment_intro) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.loginWithEmail.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_signInFragment)
        }
    }
}
