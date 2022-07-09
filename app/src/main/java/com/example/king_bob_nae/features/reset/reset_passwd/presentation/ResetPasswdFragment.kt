package com.example.king_bob_nae.features.reset.reset_passwd.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentResetPasswdBinding

class ResetPasswdFragment :
    BaseFragment<FragmentResetPasswdBinding>(R.layout.fragment_reset_passwd) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {
            btnResetPasswdBack.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_resetPasswdFragment_to_checkEmailFragment22)
            }
            btnResetPasswdNext.setOnClickListener {
                it.findNavController().navigate(R.id.action_resetPasswdFragment_to_signInFragment)
            }
        }
    }
}