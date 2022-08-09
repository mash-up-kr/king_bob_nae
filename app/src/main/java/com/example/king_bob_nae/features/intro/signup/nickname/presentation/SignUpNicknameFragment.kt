package com.example.king_bob_nae.features.intro.signup.nickname.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentSignUpNicknameBinding
import com.example.king_bob_nae.features.intro.presentation.IntroViewModel
import com.example.king_bob_nae.utils.initTextInputLayout
import com.example.king_bob_nae.utils.isValidNickname
import com.example.king_bob_nae.utils.setError
import kotlinx.coroutines.launch

class SignUpNicknameFragment :
    BaseFragment<FragmentSignUpNicknameBinding>(R.layout.fragment_sign_up_nickname) {
    private val introViewModel: IntroViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        collectFlow()
    }

    private fun initView() {
        binding.apply {
            btnSignUpNickBack.setOnClickListener {
                it.findNavController()
                    .popBackStack()
            }
            btnSignUpNickNext.setOnClickListener {
                introViewModel.checkNickname(tfSignUpNick.editText?.text.toString())
            }
            initTextInputLayout(
                tfSignUpNick,
                { tfSignUpNick.isValidNickname(tfSignUpNick.editText?.text.toString()) },
                btnSignUpNickNext
            )
        }
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                introViewModel.result.collect { authResponse ->
                    if (authResponse.checkNickname) {
                        introViewModel.setAuthNick(binding.tfSignUpNick.editText?.text.toString())
                        requireView().findNavController()
                            .navigate(R.id.action_signUpNicknameFragment_to_signUpLoadingFragment)
                    } else {
                        binding.tfSignUpNick.setError(authResponse.code)
                    }
                }
            }
        }
    }
}