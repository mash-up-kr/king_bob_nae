package com.example.king_bob_nae.features.intro.signup.select_character.presentation

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentSignUpCharacterSelectBinding

class SignUpSelectCharacterFragment :
    BaseFragment<FragmentSignUpCharacterSelectBinding>(R.layout.fragment_sign_up_character_select) {
    private lateinit var callback: OnBackPressedCallback
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        blockingBackPressed()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        initView()
    }

    private fun blockingBackPressed() {
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {}
        }
    }

    private fun initView() {
        val character = arguments?.getString("character")
        binding.apply {
            when (character) {
                "BROCCOLI" -> {
                    ivSignUpCharacter.setImageResource(R.drawable.broccoli)
                    tvSignUpCharacter.text = setTextSpan(getString(R.string.broccoli))
                    tvSignUpCharacterComment.text = getString(R.string.broccoli_comment)
                }
                "CARROT" -> {
                    ivSignUpCharacter.setImageResource(R.drawable.carrot)
                    tvSignUpCharacter.text = setTextSpan(getString(R.string.carrot))
                    tvSignUpCharacterComment.text = getString(R.string.carrot_comment)
                }
                else -> {
                    ivSignUpCharacter.setImageResource(R.drawable.green_onion)
                    tvSignUpCharacter.text = setTextSpan(getString(R.string.green_onion))
                    tvSignUpCharacterComment.text = getString(R.string.green_onion_comment)
                }
            }
            btnCharacterStart.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_signUpSelectCharacterFragment_to_signInFragment)
            }
        }
    }

    private fun setTextSpan(text: String): SpannableString {
        val text = SpannableString(text)
        val start = text.length - 3
        val end = text.length
        text.setSpan(
            ForegroundColorSpan(Color.BLACK),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return text
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

}

