package com.example.king_bob_nae.features.intro.signup.password.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentSignUpPasswdBinding
import com.example.king_bob_nae.features.intro.presentation.IntroViewModel
import com.example.king_bob_nae.utils.Extensions.Companion.PASSWD_ERROR
import com.example.king_bob_nae.utils.initTextInputLayout
import com.example.king_bob_nae.utils.isSamePasswd
import com.example.king_bob_nae.utils.isValidPasswd
import com.example.king_bob_nae.utils.setError

class SignUpPasswdFragment :
    BaseFragment<FragmentSignUpPasswdBinding>(R.layout.fragment_sign_up_passwd) {
    private val introViewModel: IntroViewModel by activityViewModels()
    private lateinit var callback: OnBackPressedCallback
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        blockingBackPressed()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        initView()
    }

    private fun initView() {
        binding.apply {
            btnSignUpPasswdBack.setOnClickListener {
                it.findNavController()
                    .popBackStack(R.id.signUpCheckCertificationFragment, inclusive = true)
            }
            btnSignUpPasswdNext.setOnClickListener {
                if (tfSignUpPasswd.isSamePasswd(
                        tfSignUpPasswd.editText?.text.toString(),
                        tfSignUpCheckPasswd.editText?.text.toString()
                    )
                ) {
                    it.findNavController()
                        .navigate(R.id.action_signUpPasswdFragment_to_signUpNicknameFragment)
                    introViewModel.setAuthPasswd(tfSignUpPasswd.editText?.text.toString())
                } else {
                    tfSignUpCheckPasswd.setError(PASSWD_ERROR)
                }
            }
            initTextInputLayout(
                tfSignUpPasswd,
                tfSignUpCheckPasswd,
                { tfSignUpPasswd.isValidPasswd(tfSignUpPasswd.editText?.text.toString()) },
                { tfSignUpCheckPasswd.isValidPasswd(tfSignUpCheckPasswd.editText?.text.toString()) },
                btnSignUpPasswdNext
            )

        }
    }

    private fun blockingBackPressed() {
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireView().findNavController()
                    .popBackStack(R.id.signUpCheckCertificationFragment, inclusive = true)
            }
        }
    }
}