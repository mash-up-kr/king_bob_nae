package com.example.king_bob_nae.features.signup.certification.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.apply {
            btnCheckCertificationBack.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_signUpCheckCertificationFragment_to_signUpEmailFragment2)
            }
            btnCheckCertificationNext.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_signUpCheckCertificationFragment_to_signUpPasswdFragment)
            }
        }
    }
}