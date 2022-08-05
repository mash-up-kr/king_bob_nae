package com.example.king_bob_nae.features.signup.nickname.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentSignUpNicknameBinding

class SignUpNicknameFragment :
    BaseFragment<FragmentSignUpNicknameBinding>(R.layout.fragment_sign_up_nickname) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {
            btnSignUpNickBack.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_signUpNicknameFragment_to_signUpPasswdFragment)
            }
            btnSignUpNickNext.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_signUpNicknameFragment_to_signUpLoadingFragment)
            }
        }
    }
}
