package com.example.king_bob_nae.features.create.detail.presentaion

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentDetailKkiLogBinding

class DetailKkiLogFragment :
    BaseFragment<FragmentDetailKkiLogBinding>(R.layout.fragment_detail_kki_log) {
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
                navController.popBackStack()
            }

            tvFinish.setOnClickListener {
                navController.navigate(R.id.action_detailKkiLogFragment_to_detailKkiLogResultFragment)
            }
        }
    }
}
