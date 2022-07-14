package com.example.king_bob_nae.features.intro.signup.select_character.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentSignUpCharacterSelectBinding

class SignUpSelectCharacterFragment :
    BaseFragment<FragmentSignUpCharacterSelectBinding>(R.layout.fragment_sign_up_character_select) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.btnCharacterStart.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_signUpSelectCharacterFragment_to_signInFragment)
        }
    }
}