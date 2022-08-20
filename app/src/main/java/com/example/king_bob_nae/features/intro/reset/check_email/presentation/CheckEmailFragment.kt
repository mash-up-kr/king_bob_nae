package com.example.king_bob_nae.features.intro.reset.check_email.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentCheckEmailBinding
import com.example.king_bob_nae.features.intro.presentation.IntroViewModel
import com.example.king_bob_nae.shared.setOnThrottleClickListener
import com.example.king_bob_nae.utils.initTextInputLayout
import com.example.king_bob_nae.utils.isValidEmail
import com.example.king_bob_nae.utils.setError
import kotlinx.coroutines.launch

class CheckEmailFragment : BaseFragment<FragmentCheckEmailBinding>(R.layout.fragment_check_email) {
    private val introViewModel: IntroViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        collectFlow()
    }

    private fun initView() {
        binding.apply {

            btnCheckEmailBack.setOnClickListener {
                it.findNavController().popBackStack()
            }

            btnCheckEmailNext.setOnThrottleClickListener {
                introViewModel.checkEmailExistence(tfSignUpCheckCertification.editText?.text.toString())
            }

            initTextInputLayout(
                tfSignUpCheckCertification,
                { tfSignUpCheckCertification.isValidEmail(tfSignUpCheckCertification.editText?.text.toString()) },
                btnCheckEmailNext
            )

        }
    }

    private fun collectFlow() {
        val bundle = Bundle()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                introViewModel.result.collect { authResponse ->
                    if (authResponse.checkEmailExistence) {
                        bundle.putString(
                            "email",
                            binding.tfSignUpCheckCertification.editText?.text.toString()
                        )
                        requireView().findNavController()
                            .navigate(
                                R.id.action_checkEmailFragment_to_checkCertificationFragment,
                                bundle
                            )
                    } else {
                        binding.tfSignUpCheckCertification.setError(authResponse.code)
                    }
                }
            }
        }
    }
}