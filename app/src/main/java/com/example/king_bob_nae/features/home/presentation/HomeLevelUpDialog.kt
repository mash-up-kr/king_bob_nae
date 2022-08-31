package com.example.king_bob_nae.features.home.presentation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.activityViewModels
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseDialogFragment
import com.example.king_bob_nae.databinding.DialogLevelUpBinding
import com.example.king_bob_nae.features.home.presentation.viewmodel.HomeViewModel

class HomeLevelUpDialog : BaseDialogFragment<DialogLevelUpBinding>(R.layout.dialog_level_up) {
    private val homeViewModel by activityViewModels<HomeViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestWindowFeature(Window.FEATURE_NO_TITLE)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.btnLevelUp.setOnClickListener {
            dialog?.dismiss()
            homeViewModel.getHomeStatus()
        }
    }
}
