package com.example.king_bob_nae.features.home.presentation

import android.os.Bundle
import android.view.View
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseDialogFragment
import com.example.king_bob_nae.databinding.DialogLevelUpBinding

class HomeLevelUpDialog : BaseDialogFragment<DialogLevelUpBinding>(R.layout.dialog_level_up) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLevelUp.setOnClickListener {
            dialog?.dismiss()
        }
    }
}
