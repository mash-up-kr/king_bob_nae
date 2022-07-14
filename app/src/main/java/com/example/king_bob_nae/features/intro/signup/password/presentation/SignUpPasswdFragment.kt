package com.example.king_bob_nae.features.intro.signup.password.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentSignUpPasswdBinding
import com.example.king_bob_nae.features.intro.presentation.IntroViewModel
import com.example.king_bob_nae.util.Extensions.Companion.PASSWD_ERROR
import com.example.king_bob_nae.util.hideIcon
import com.example.king_bob_nae.util.isValid
import com.example.king_bob_nae.util.setButtonEnable
import com.example.king_bob_nae.util.setError

class SignUpPasswdFragment :
    BaseFragment<FragmentSignUpPasswdBinding>(R.layout.fragment_sign_up_passwd) {
    private val viewModel: IntroViewModel by activityViewModels()
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
                if(viewModel.isSamePasswd(
                        tfSignUpPasswd.editText?.text.toString(),
                        tfSignUpCheckPasswd.editText?.text.toString()
                )) it.findNavController()
                    .navigate(R.id.action_signUpPasswdFragment_to_signUpNicknameFragment)
                else tfSignUpCheckPasswd.setError(PASSWD_ERROR)
            }

            tfSignUpPasswd.apply {
                hideIcon()
                isValid {
                    viewModel.isValidatePasswd(it)
                }
                setButtonEnable(tfSignUpCheckPasswd,btnSignUpPasswdNext)
            }

            tfSignUpCheckPasswd.apply {
                hideIcon()
                isValid {
                    viewModel.isValidatePasswd(it)
                }
                setButtonEnable(tfSignUpPasswd,btnSignUpPasswdNext)
            }
        }
    }
}