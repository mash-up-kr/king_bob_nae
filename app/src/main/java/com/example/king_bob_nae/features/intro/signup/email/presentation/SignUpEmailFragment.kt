package com.example.king_bob_nae.features.intro.signup.email.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentSignUpEmailBinding
import com.example.king_bob_nae.features.intro.presentation.IntroViewModel
import com.example.king_bob_nae.shared.setOnThrottleClickListener
import com.example.king_bob_nae.utils.initTextInputLayout
import com.example.king_bob_nae.utils.isValidEmail
import com.example.king_bob_nae.utils.setError
import kotlinx.coroutines.launch

class SignUpEmailFragment :
    BaseFragment<FragmentSignUpEmailBinding>(R.layout.fragment_sign_up_email) {
    private val introViewModel: IntroViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = Bundle()
        initView(bundle)
        collectFlow(bundle)
    }

    private fun initView(bundle: Bundle) {
        binding.apply {
            btnSignUpEmailBack.setOnClickListener {
                it.findNavController().navigate(R.id.action_signUpEmailFragment_to_introFragment)
            }
            btnSignUpEmailNext.setOnThrottleClickListener() {
                bundle.putString("email", tfSignUpEmail.editText?.text.toString())
                introViewModel.checkEmailDuplicated(tfSignUpEmail.editText?.text.toString())
            }
            initTextInputLayout(
                tfSignUpEmail,
                { tfSignUpEmail.isValidEmail(tfSignUpEmail.editText?.text.toString()) },
                btnSignUpEmailNext
            )
        }
    }

    private fun collectFlow(bundle: Bundle) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    introViewModel.result.collect { authResponse ->
                        if (authResponse.success == true) {
                            requireView().findNavController()
                                .navigate(
                                    R.id.action_signUpEmailFragment_to_signUpCheckCertificationFragment,
                                    bundle
                                )
                        } else {
                            binding.tfSignUpEmail.setError(authResponse.code)
                        }
                    }
                }
            }
        }
    }
}