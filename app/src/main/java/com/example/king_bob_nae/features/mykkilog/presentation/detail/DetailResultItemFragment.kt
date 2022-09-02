package com.example.king_bob_nae.features.mykkilog.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentDetailResultItemBinding

class DetailResultItemFragment :
    BaseFragment<FragmentDetailResultItemBinding>(R.layout.fragment_detail_result_item) {

    private val stepAdapter = DetailStepAdapter(emptyList())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView() {
        binding.apply {
            ivItemCancel.setOnClickListener {
                it.findNavController().popBackStack()
            }
            vpDetailStep.apply {
                adapter = stepAdapter
                setCurrentItem(getStep(), false)
            }
        }
    }

    // TODO 클릭한 아이텝 순서
    private fun getStep() = 0
}