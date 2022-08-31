package com.example.king_bob_nae.features.create.detail.presentaion.result

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentDetailKkiLogResultBinding
import com.example.king_bob_nae.features.create.detail.presentaion.DetailKkiLogViewModel

class DetailKkiLogResultFragment :
    BaseFragment<FragmentDetailKkiLogResultBinding>(R.layout.fragment_detail_kki_log_result) {
    private val detailKkiLogViewModel by activityViewModels<DetailKkiLogViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.viewModel = detailKkiLogViewModel

        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.run {
            ivMore.setOnClickListener { view ->
                PopupMenu(root.context, view).apply {
                    menuInflater.inflate(R.menu.menu_kki_log_feed, this.menu)
                    setOnMenuItemClickListener { // TODO menu item click 처리
                        true
                    }
                }.show()
            }

            ivBack.setOnClickListener {
                navController.popBackStack()
            }
        }
    }
}
