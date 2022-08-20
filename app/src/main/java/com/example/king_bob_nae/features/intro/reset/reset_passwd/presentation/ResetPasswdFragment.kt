package com.example.king_bob_nae.features.intro.reset.reset_passwd.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentResetPasswdBinding
import com.example.king_bob_nae.features.intro.presentation.IntroViewModel
import com.example.king_bob_nae.shared.setOnThrottleClickListener
import com.example.king_bob_nae.utils.Extensions.Companion.PASSWD_ERROR
import com.example.king_bob_nae.utils.initTextInputLayout
import com.example.king_bob_nae.utils.isSamePasswd
import com.example.king_bob_nae.utils.isValidPasswd
import com.example.king_bob_nae.utils.setError
import kotlinx.coroutines.launch

class ResetPasswdFragment :
    BaseFragment<FragmentResetPasswdBinding>(R.layout.fragment_reset_passwd) {
    private val introViewModel: IntroViewModel by activityViewModels()
    private lateinit var callback: OnBackPressedCallback
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        blockingBackPressed()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        initView()
        collectFlow()
    }

    private fun initView() {
        binding.apply {
            btnResetPasswdBack.setOnClickListener {
                it.findNavController()
                    .popBackStack(R.id.checkCertificationFragment, inclusive = true)
            }
            btnResetPasswdNext.setOnThrottleClickListener {
                if (tfResetPasswd.isSamePasswd(
                        tfResetPasswd.editText?.text.toString(),
                        tfResetCheckPasswd.editText?.text.toString()
                    )
                ) {
                    introViewModel.resetPassword(
                        tfResetPasswd.editText?.text.toString(),
                        tfResetCheckPasswd.editText?.text.toString()
                    )
                } else {
                    tfResetCheckPasswd.setError(PASSWD_ERROR)
                }
            }

            initTextInputLayout(
                tfResetPasswd,
                tfResetCheckPasswd,
                { tfResetPasswd.isValidPasswd(tfResetPasswd.editText?.text.toString()) },
                { tfResetCheckPasswd.isValidPasswd(tfResetCheckPasswd.editText?.text.toString()) },
                btnResetPasswdNext
            )

        }
    }

    private fun blockingBackPressed() {
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireView().findNavController()
                    .popBackStack(R.id.checkCertificationFragment, inclusive = true)
            }
        }
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                introViewModel.result.collect { authResponse ->
                    if (authResponse.resetPassword) {
                        requireView().findNavController()
                            .navigate(R.id.action_resetPasswdFragment_to_signInFragment)
                    } else {
                        binding.tfResetCheckPasswd.setError(authResponse.code)
                    }
                }
            }
        }
    }
}