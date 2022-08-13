package com.example.king_bob_nae.features.intro.reset.check_certification.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentCheckCertificationBinding

class CheckCertificationFragment :
    BaseFragment<FragmentCheckCertificationBinding>(R.layout.fragment_check_certification) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {
            btnCheckCertificationBack.setOnClickListener {
                it.findNavController().popBackStack()
            }
            btnCheckCertificationNext.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_checkCertificationFragment_to_resetPasswdFragment)
            }
        }
    }

}