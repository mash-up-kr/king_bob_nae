package com.example.king_bob_nae.features.intro.signin.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentSignInBinding
import com.example.king_bob_nae.features.intro.presentation.IntroViewModel
import com.example.king_bob_nae.util.hideIcon
import com.example.king_bob_nae.util.isValid
import com.example.king_bob_nae.util.setButtonEnable

class SignInFragment : BaseFragment<FragmentSignInBinding>(R.layout.fragment_sign_in) {
    private val viewModel: IntroViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    private fun initView() {
        binding.apply {
            btnBack.setOnClickListener {
                it.findNavController().navigate(R.id.action_signInFragment_to_introFragment)
            }
            tvFindPasswd.setOnClickListener {
                it.findNavController().navigate(R.id.action_signInFragment_to_checkEmailFragment2)
            }

            tfSignInEmail.apply {
                hideIcon()
                isValid {
                    viewModel.isValidateEmail(it)
                }
                setButtonEnable(tfSignInPasswd, btnFinish)
            }

            tfSignInPasswd.apply {
                hideIcon()
                isValid {
                    viewModel.isValidatePasswd(it)
                }
                setButtonEnable(tfSignInEmail, btnFinish)
            }
        }
    }
}
