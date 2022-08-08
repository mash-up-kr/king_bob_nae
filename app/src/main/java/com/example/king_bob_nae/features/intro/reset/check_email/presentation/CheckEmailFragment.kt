package com.example.king_bob_nae.features.intro.reset.check_email.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentCheckEmailBinding
import com.example.king_bob_nae.features.intro.presentation.IntroViewModel
import com.example.king_bob_nae.utils.initTextInputLayout
import com.example.king_bob_nae.utils.isValidEmail

class CheckEmailFragment : BaseFragment<FragmentCheckEmailBinding>(R.layout.fragment_check_email) {
    private val introViewModel: IntroViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {

            btnCheckEmailBack.setOnClickListener {
                it.findNavController().navigate(R.id.action_checkEmailFragment_to_signInFragment)
            }

            btnCheckEmailNext.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_checkEmailFragment_to_checkCertificationFragment)
            }

            initTextInputLayout(
                tfSignUpCheckCertification,
                { tfSignUpCheckCertification.isValidEmail(tfSignUpCheckCertification.editText?.text.toString()) },
                btnCheckEmailNext
            )

        }
    }
}