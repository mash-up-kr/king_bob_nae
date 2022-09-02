package com.example.king_bob_nae.features.create.detail.presentaion.result.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.ItemDetailKkiLogResultIngredientBinding
import com.example.king_bob_nae.features.create.detail.presentaion.result.DetailKkiLogResultViewModel

class ResultIngredientViewHolder(
    private val binding: ItemDetailKkiLogResultIngredientBinding,
    detailKkiLogResultViewModel: DetailKkiLogResultViewModel
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.viewModel = detailKkiLogResultViewModel
    }

    fun bind(item: String) {

        binding.run {
            this.item = item
            executePendingBindings()
        }
    }
}
