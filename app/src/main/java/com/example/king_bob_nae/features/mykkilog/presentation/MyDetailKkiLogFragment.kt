package com.example.king_bob_nae.features.mykkilog.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentMyDetailKkilogBinding
import com.example.king_bob_nae.features.mykkilog.data.MyKkiLogThumbNail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyDetailKkiLogFragment :
    BaseFragment<FragmentMyDetailKkilogBinding>(R.layout.fragment_my_detail_kkilog) {
    private val itemAdapter: MyKkiLogAdapter by lazy {
        MyKkiLogAdapter(
            itemClickListener = { doOnClick(it) }
        )
    }

    private val myKkiLogViewModel: MyKkiLogViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectFlow()
        initView()
        myKkiLogViewModel.getDetailKkiLog()
    }

    private fun initView() {
        binding.rvMyDetailKkilog.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = itemAdapter
        }
    }

    private fun showEmptyMark(state: Boolean) {
        binding.apply {
            tvEmpty.isVisible = state
            ivEmptyMark.isVisible = state
        }
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                myKkiLogViewModel.myDetailKkiLog.collect { myDetailKkiLogList ->
                    showEmptyMark(myDetailKkiLogList.isNullOrEmpty())
                    itemAdapter.submitList(myDetailKkiLogList?.toList())
                }
            }
        }
    }

    private fun doOnClick(item: MyKkiLogThumbNail) {
        //TODO 기록화면으로 id 넘겨주며 이동
    }
}