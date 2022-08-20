package com.example.king_bob_nae.features.myprofile.presentation.setting

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentSettingBinding

class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {
            btnSettingBack.setOnClickListener {
                it.findNavController().popBackStack()
            }

            tvLogout.setOnClickListener {
                showLogoutDialog()
            }

            tvDeleteAccount.setOnClickListener {
                showDeleteDialog()
            }
        }
    }

    private fun showDeleteDialog() {
        val dialog = DeleteDialog()
        dialog.show(requireActivity().supportFragmentManager, "DeleteDialog")
    }

    private fun showLogoutDialog() {
        val dialog = LogoutDialog()
        dialog.show(requireActivity().supportFragmentManager, "LogoutDialog")
    }
}