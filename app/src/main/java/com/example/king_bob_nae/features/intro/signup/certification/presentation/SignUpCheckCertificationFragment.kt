package com.example.king_bob_nae.features.intro.signup.certification.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentSignUpCheckCertificationBinding

class SignUpCheckCertificationFragment :
    BaseFragment<FragmentSignUpCheckCertificationBinding>(R.layout.fragment_sign_up_check_certification) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val email = arguments?.getString("email")
        binding.apply {
            tfSignUpCheckCertification.helperText =
                "다음으로 전송된 인증번호 : $email\n" + getString(R.string.certification_send)
            btnCheckCertificationBack.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_signUpCheckCertificationFragment_to_signUpEmailFragment)
            }
            btnCheckCertificationNext.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_signUpCheckCertificationFragment_to_signUpPasswdFragment)
            }
        }
    }
}