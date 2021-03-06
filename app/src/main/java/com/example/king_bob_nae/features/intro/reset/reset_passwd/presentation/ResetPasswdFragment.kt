package com.example.king_bob_nae.features.intro.reset.reset_passwd.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentResetPasswdBinding
import com.example.king_bob_nae.features.intro.presentation.IntroViewModel
import com.example.king_bob_nae.util.Extensions.Companion.PASSWD_ERROR
import com.example.king_bob_nae.util.initTextInputLayout
import com.example.king_bob_nae.util.setError

class ResetPasswdFragment :
    BaseFragment<FragmentResetPasswdBinding>(R.layout.fragment_reset_passwd) {
    private val introViewModel: IntroViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {
            btnResetPasswdBack.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_resetPasswdFragment_to_checkEmailFragment)
            }
            btnResetPasswdNext.setOnClickListener {
                if (introViewModel.isSamePasswd(
                        tfResetPasswd.editText?.text.toString(),
                        tfResetCheckPasswd.editText?.text.toString()
                    )
                ) it.findNavController().navigate(R.id.action_resetPasswdFragment_to_signInFragment)
                else tfResetCheckPasswd.setError(PASSWD_ERROR)
            }

            initTextInputLayout(
                tfResetPasswd,
                tfResetCheckPasswd,
                { introViewModel.isValidatePasswd(tfResetPasswd.editText?.text.toString()) },
                { introViewModel.isValidatePasswd(tfResetCheckPasswd.editText?.text.toString()) },
                btnResetPasswdNext
            )

        }
    }
}