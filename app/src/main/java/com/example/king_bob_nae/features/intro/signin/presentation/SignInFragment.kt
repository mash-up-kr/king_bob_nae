package com.example.king_bob_nae.features.intro.signin.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentSignInBinding
import com.example.king_bob_nae.features.HomeActivity
import com.example.king_bob_nae.features.intro.presentation.IntroViewModel
import com.example.king_bob_nae.shared.setOnThrottleClickListener
import com.example.king_bob_nae.utils.initTextInputLayout
import com.example.king_bob_nae.utils.isValidEmail
import com.example.king_bob_nae.utils.isValidPasswd
import com.example.king_bob_nae.utils.setError
import kotlinx.coroutines.launch

class SignInFragment : BaseFragment<FragmentSignInBinding>(R.layout.fragment_sign_in) {
    private val introViewModel: IntroViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        collectFlow()
    }

    private fun initView() {
        binding.apply {
            btnBack.setOnClickListener {
                it.findNavController().popBackStack()
            }
            tvFindPasswd.setOnClickListener {
                it.findNavController().navigate(R.id.action_signInFragment_to_checkEmailFragment)
            }
            btnFinish.setOnThrottleClickListener {
                introViewModel.signIn(
                    tfSignInEmail.editText?.text.toString(),
                    tfSignInPasswd.editText?.text.toString()
                )
            }
            initTextInputLayout(
                tfSignInEmail,
                tfSignInPasswd,
                { tfSignInEmail.isValidEmail(tfSignInEmail.editText?.text.toString()) },
                { tfSignInPasswd.isValidPasswd(tfSignInPasswd.editText?.text.toString()) },
                btnFinish
            )
        }
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                introViewModel.signInResult.collect { signInResponse ->
                    if (signInResponse.result) {
                        startActivity(Intent(requireContext(), HomeActivity::class.java))
                    } else {
                        binding.tfSignInPasswd.setError(signInResponse.code)
                    }
                }
            }
        }
    }
}
