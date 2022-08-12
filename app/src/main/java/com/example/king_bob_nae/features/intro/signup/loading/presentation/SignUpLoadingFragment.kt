package com.example.king_bob_nae.features.intro.signup.loading.presentation

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
import com.example.king_bob_nae.databinding.FragmentSignUpLoadingBinding
import com.example.king_bob_nae.features.intro.data.dto.CHARACTER
import com.example.king_bob_nae.features.intro.presentation.IntroViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpLoadingFragment :
    BaseFragment<FragmentSignUpLoadingBinding>(R.layout.fragment_sign_up_loading) {
    private val introViewModel: IntroViewModel by activityViewModels()
    private lateinit var character: CHARACTER
    private lateinit var callback: OnBackPressedCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {}
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        introViewModel.signUp()
        val bundle = Bundle()
        collectFlow(bundle)
    }

    private fun collectFlow(bundle: Bundle) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                introViewModel.character.collect { character ->
                    this@SignUpLoadingFragment.character = character
                    bundle.putString("character", character.name)
                    delay(3000L)
                    requireView().findNavController()
                        .navigate(
                            R.id.action_signUpLoadingFragment_to_signUpSelectCharacterFragment,
                            bundle
                        )
                }
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}

