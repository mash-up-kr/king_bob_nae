package com.example.king_bob_nae.features.intro.signup.email.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentSignUpEmailBinding
import com.example.king_bob_nae.features.intro.presentation.IntroViewModel
import com.example.king_bob_nae.utils.initTextInputLayout

class SignUpEmailFragment :
    BaseFragment<FragmentSignUpEmailBinding>(R.layout.fragment_sign_up_email) {
    private val introViewModel: IntroViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {
            btnSignUpEmailBack.setOnClickListener {
                it.findNavController().navigate(R.id.action_signUpEmailFragment_to_introFragment)
            }
            btnSignUpEmailNext.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_signUpEmailFragment_to_signUpCheckCertificationFragment)
            }
            initTextInputLayout(
                tfSignUpEmail,
                { introViewModel.isValidateEmail(tfSignUpEmail.editText?.text.toString()) },
                btnSignUpEmailNext
            )
        }
    }
}