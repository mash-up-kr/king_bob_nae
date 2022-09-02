package com.example.king_bob_nae.features.create.detail.presentaion.item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentDetailKkiLogResultItemBinding
import com.example.king_bob_nae.features.create.detail.presentaion.DetailKkiLogSharedViewModel
import com.example.king_bob_nae.utils.NLog
import kotlinx.coroutines.launch

class DetailKkiLogResultItemFragment :
    BaseFragment<FragmentDetailKkiLogResultItemBinding>(R.layout.fragment_detail_kki_log_result_item) {
    private val detailKkiLogSharedViewModel by activityViewModels<DetailKkiLogSharedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        collectFlows()
    }

    private fun initView() {
    }

    private fun collectFlows() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // first: position, second: 레시피 리스트 , position은 1부터 시작
                detailKkiLogSharedViewModel.recipePair.collect {
                    NLog.d("kelly list", "${it.first} +/+ ${it.second}")
                }
            }
        }
    }
}
