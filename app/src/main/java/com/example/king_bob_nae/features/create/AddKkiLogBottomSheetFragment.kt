package com.example.king_bob_nae.features.create

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseBottomSheetDialogFragment
import com.example.king_bob_nae.databinding.FragmentAddKkiLogBottomSheetBinding

class AddKkiLogBottomSheetFragment :
    BaseBottomSheetDialogFragment<FragmentAddKkiLogBottomSheetBinding>(R.layout.fragment_add_kki_log_bottom_sheet) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.run {
            ivClose.setOnClickListener {
                dismiss()
            }

            llSimpleKkiLog.setOnClickListener {
                navController.navigate(R.id.imagePickerFragment)
                dismiss()
            }

            llDetailKkiLog.setOnClickListener {
                navController.navigate(R.id.detailKkiLogFragment)
                dismiss()
            }
        }
    }
}
