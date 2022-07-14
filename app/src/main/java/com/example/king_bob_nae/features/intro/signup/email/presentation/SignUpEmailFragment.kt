package com.example.king_bob_nae.features.intro.signup.email.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentSignUpEmailBinding
import com.example.king_bob_nae.features.intro.presentation.IntroViewModel
import com.example.king_bob_nae.util.hideIcon
import com.example.king_bob_nae.util.isValid
import com.example.king_bob_nae.util.setButtonEnable

class SignUpEmailFragment :
    BaseFragment<FragmentSignUpEmailBinding>(R.layout.fragment_sign_up_email) {
    private val viewModel: IntroViewModel by activityViewModels()
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

            tfSignUpEmail.apply {
                hideIcon()
                isValid {
                    viewModel.isValidateEmail(it)
                }
                setButtonEnable(tfSignUpEmail,btnSignUpEmailNext)
            }

        }
    }
}