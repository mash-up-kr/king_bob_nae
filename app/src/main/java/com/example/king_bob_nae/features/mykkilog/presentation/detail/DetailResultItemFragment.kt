package com.example.king_bob_nae.features.mykkilog.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentDetailResultItemBinding
import com.example.king_bob_nae.features.create.detail.presentaion.DetailKkiLogSharedViewModel
import com.example.king_bob_nae.features.mykkilog.presentation.detail.domain.StepItem
import com.example.king_bob_nae.features.mykkilog.presentation.detail.domain.toStep
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailResultItemFragment :
    BaseFragment<FragmentDetailResultItemBinding>(R.layout.fragment_detail_result_item) {
    private val detailKkiLogSharedViewModel by activityViewModels<DetailKkiLogSharedViewModel>()
    lateinit var stepList: List<StepItem>
    lateinit var stepAdapter: DetailStepAdapter

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

        }
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailKkiLogSharedViewModel.recipePair.collectLatest { pair ->
                    stepAdapter =
                        DetailStepAdapter(pair.second.toStep(), detailKkiLogSharedViewModel)
                    binding.vpDetailStep.apply {
                        adapter = stepAdapter
                        doOnPreDraw {
                            currentItem = pair.first - 1
                        }
                    }
                }
            }
        }
    }
}