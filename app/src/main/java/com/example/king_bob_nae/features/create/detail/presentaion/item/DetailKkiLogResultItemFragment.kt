package com.example.king_bob_nae.features.create.detail.presentaion.item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentDetailResultItemBinding
import com.example.king_bob_nae.features.create.detail.presentaion.DetailKkiLogSharedViewModel
import com.example.king_bob_nae.features.mykkilog.presentation.detail.DetailStepAdapter
import com.example.king_bob_nae.features.mykkilog.presentation.detail.domain.toStep
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailKkiLogResultItemFragment :
    BaseFragment<FragmentDetailResultItemBinding>(R.layout.fragment_detail_result_item) {
    private val detailKkiLogSharedViewModel by activityViewModels<DetailKkiLogSharedViewModel>()
    private val stepAdapter = DetailStepAdapter(getSteps(), detailKkiLogSharedViewModel)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        collectFlow()
    }

    private fun initView() {
        binding.apply {
            ivItemCancel.setOnClickListener {
                it.findNavController().popBackStack()
            }
            vpDetailStep.apply {
                adapter = stepAdapter
            }
        }
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailKkiLogSharedViewModel.recipePair.collectLatest { pair ->
                    binding.vpDetailStep.apply {
                        setCurrentItem(pair.first, false)
                    }
                }
            }
        }
    }

    private fun getSteps() =
        detailKkiLogSharedViewModel.recipePair.value.second.toStep()
}
