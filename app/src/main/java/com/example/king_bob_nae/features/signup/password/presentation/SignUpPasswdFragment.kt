package com.example.king_bob_nae.features.signup.password.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentSignUpPasswdBinding

class SignUpPasswdFragment :
    BaseFragment<FragmentSignUpPasswdBinding>(R.layout.fragment_sign_up_passwd) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {
            btnSignUpPasswdBack.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_signUpPasswdFragment_to_signUpEmailFragment2)
            }
            btnSignUpPasswdNext.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_signUpPasswdFragment_to_signUpNicknameFragment)
            }
        }
    }
}