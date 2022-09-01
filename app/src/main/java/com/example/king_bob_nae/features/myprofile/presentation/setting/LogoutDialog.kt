package com.example.king_bob_nae.features.myprofile.presentation.setting

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import com.example.king_bob_nae.R
import com.example.king_bob_nae.SharedPreferences
import com.example.king_bob_nae.base.BaseDialogFragment
import com.example.king_bob_nae.databinding.DialogLogoutBinding
import com.example.king_bob_nae.features.intro.presentation.IntroActivity
import com.example.king_bob_nae.shared.setOnThrottleClickListener

class LogoutDialog : BaseDialogFragment<DialogLogoutBinding>(R.layout.dialog_logout) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
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
        binding.btnLogout.setOnThrottleClickListener {
            SharedPreferences.clearAccessToken()
            Intent(requireContext(), IntroActivity::class.java).apply {
                startActivity(this)
            }
            requireActivity().finishAffinity()
        }
        binding.btnLogoutCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}
