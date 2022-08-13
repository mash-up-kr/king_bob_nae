package com.example.king_bob_nae.features.intro.signup.certification.presentation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseDialogFragment
import com.example.king_bob_nae.databinding.DialogResendCertificationBinding

class SignUpCheckDialog :
    BaseDialogFragment<DialogResendCertificationBinding>(R.layout.dialog_resend_certification) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = super.onCreateView(inflater, container, savedInstanceState)
        initView()
        return binding
    }

    private fun initView() {
        dialog?.apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestWindowFeature(Window.FEATURE_NO_TITLE)
        }
        binding.btnResendCheck.setOnClickListener {
            dismiss()
        }
    }
}