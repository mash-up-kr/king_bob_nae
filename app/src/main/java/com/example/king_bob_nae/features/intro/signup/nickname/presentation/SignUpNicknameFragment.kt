package com.example.king_bob_nae.features.intro.signup.nickname.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentSignUpNicknameBinding
import com.example.king_bob_nae.features.intro.presentation.IntroViewModel
import com.example.king_bob_nae.util.initTextInputLayout

class SignUpNicknameFragment :
    BaseFragment<FragmentSignUpNicknameBinding>(R.layout.fragment_sign_up_nickname) {
    private val introViewModel: IntroViewModel by activityViewModels()
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
            initTextInputLayout(
                tfSignUpNick,
                { introViewModel.isValidateNickname(tfSignUpNick.editText?.text.toString()) },
                btnSignUpNickNext
            )
        }
    }
}