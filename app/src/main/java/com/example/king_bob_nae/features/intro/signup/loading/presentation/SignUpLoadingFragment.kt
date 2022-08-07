package com.example.king_bob_nae.features.intro.signup.loading.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentSignUpLoadingBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpLoadingFragment :
    BaseFragment<FragmentSignUpLoadingBinding>(R.layout.fragment_sign_up_loading) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        lifecycleScope.launch {
            delay(3000L)
            this@SignUpLoadingFragment.findNavController()
                .navigate(R.id.action_signUpLoadingFragment_to_signUpSelectCharacterFragment)
        }
    }
}
