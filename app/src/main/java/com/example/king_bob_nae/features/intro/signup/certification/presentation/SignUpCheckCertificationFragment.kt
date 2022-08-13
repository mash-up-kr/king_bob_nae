package com.example.king_bob_nae.features.intro.signup.certification.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentSignUpCheckCertificationBinding
import com.example.king_bob_nae.features.intro.data.dto.TYPE
import com.example.king_bob_nae.features.intro.presentation.IntroViewModel
import com.example.king_bob_nae.shared.setOnThrottleClickListener
import com.example.king_bob_nae.utils.setCertificateButtonEnable
import com.example.king_bob_nae.utils.setError
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SignUpCheckCertificationFragment :
    BaseFragment<FragmentSignUpCheckCertificationBinding>(R.layout.fragment_sign_up_check_certification) {
    private val introViewModel: IntroViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = arguments?.getString("email")
        val type = TYPE.SIGN_UP
        email?.let { initView(it, type) }

        sendCertification(email.toString(), type)
        collectFlow()
    }

    private fun initView(email: String, type: TYPE) {
        binding.apply {
            tfSignUpCheckCertification.helperText =
                getString(R.string.certification_send_first) + "$email\n" + getString(R.string.certification_send_second)

            btnCheckCertificationBack.setOnClickListener {
                it.findNavController().popBackStack()
            }

            btnCheckCertificationNext.setOnThrottleClickListener() {
                introViewModel.checkCertification(
                    email,
                    type,
                    tfSignUpCheckCertification.editText?.text.toString()
                )
            }

            tfSignUpCheckCertification.apply {
                setCertificateButtonEnable(btnCheckCertificationNext)
                setErrorIconOnClickListener {
                    sendCertification(email, type)
                }
            }
        }
    }


    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                introViewModel.result.collectLatest { authResponse ->
                    if (authResponse.checkCertification) {
                        requireView().findNavController()
                            .navigate(R.id.action_signUpCheckCertificationFragment_to_signUpPasswdFragment)
                    } else {
                        binding.tfSignUpCheckCertification.setError(authResponse.code)
                    }
                }
            }
        }
    }

    private fun sendCertification(email: String, type: TYPE) {
        introViewModel.createCertification(email, type)
    }
}

