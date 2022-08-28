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
import com.example.king_bob_nae.databinding.FragmentMySimpleKkilogBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MySimpleKkiLogFragment :
    BaseFragment<FragmentMySimpleKkilogBinding>(R.layout.fragment_my_simple_kkilog) {
    private val itemAdapter: MyKkiLogAdapter by lazy { MyKkiLogAdapter() }

    private val myKkiLogViewModel: MyKkiLogViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myKkiLogViewModel.getKkiLogList()
        collectFlow()
        initView()
    }

    private fun initView() {
        binding.rvMySimpleKkilog.apply {
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
                myKkiLogViewModel.myKkiLog.collectLatest { myKkiLogList ->
                    showEmptyMark(myKkiLogList.isNullOrEmpty())
                    itemAdapter.submitList(myKkiLogList?.toList())
                }
            }
        }
    }

}