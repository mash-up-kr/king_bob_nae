package com.example.king_bob_nae.features.reset.check_email.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentCheckEmailBinding

class CheckEmailFragment : BaseFragment<FragmentCheckEmailBinding>(R.layout.fragment_check_email) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {
            btnCheckEmailBack.setOnClickListener {
                it.findNavController().navigate(R.id.action_checkEmailFragment2_to_signInFragment)
            }
            btnCheckEmailNext.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_checkEmailFragment2_to_checkCertificationFragment)
            }
        }
    }
}
