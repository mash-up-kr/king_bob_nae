package com.example.king_bob_nae.features.intro.signup.password.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentSignUpPasswdBinding
import com.example.king_bob_nae.features.intro.presentation.IntroViewModel
import com.example.king_bob_nae.utils.Extensions.Companion.PASSWD_ERROR
import com.example.king_bob_nae.utils.initTextInputLayout
import com.example.king_bob_nae.utils.setError

class SignUpPasswdFragment :
    BaseFragment<FragmentSignUpPasswdBinding>(R.layout.fragment_sign_up_passwd) {
    private val introViewModel: IntroViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {
            btnSignUpPasswdBack.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_signUpPasswdFragment_to_signUpEmailFragment)
            }
            btnSignUpPasswdNext.setOnClickListener {
                if (introViewModel.isSamePasswd(
                        tfSignUpPasswd.editText?.text.toString(),
                        tfSignUpCheckPasswd.editText?.text.toString()
                    )
                ) it.findNavController()
                    .navigate(R.id.action_signUpPasswdFragment_to_signUpNicknameFragment)
                else tfSignUpCheckPasswd.setError(PASSWD_ERROR)
            }


            initTextInputLayout(
                tfSignUpPasswd,
                tfSignUpCheckPasswd,
                { introViewModel.isValidatePasswd(tfSignUpPasswd.editText?.text.toString()) },
                { introViewModel.isValidatePasswd(tfSignUpCheckPasswd.editText?.text.toString()) },
                btnSignUpPasswdNext
            )

        }
    }
}