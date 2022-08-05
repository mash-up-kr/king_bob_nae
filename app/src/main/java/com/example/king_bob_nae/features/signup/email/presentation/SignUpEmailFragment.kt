package com.example.king_bob_nae.features.signup.email.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentSignUpEmailBinding

class SignUpEmailFragment :
    BaseFragment<FragmentSignUpEmailBinding>(R.layout.fragment_sign_up_email) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {
            Log.d("kelly1", findNavController().currentDestination.toString())
            btnSignUpEmailBack.setOnClickListener {
                it.findNavController().navigate(R.id.action_signUpEmailFragment2_to_introFragment)
            }
            btnSignUpEmailNext.setOnClickListener {
                Log.d("kelly2", findNavController().currentDestination.toString())
                it.findNavController()
                    .navigate(R.id.action_signUpEmailFragment2_to_signUpCheckCertificationFragment2)
            }
        }
    }
}
