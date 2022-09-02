package com.example.king_bob_nae.features.create.detail.presentaion.result.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.ItemDetailKkiLogResultRecipeBinding
import com.example.king_bob_nae.features.create.detail.data.Recipe
import com.example.king_bob_nae.features.create.detail.presentaion.result.DetailKkiLogResultViewModel

class ResultRecipeViewHolder(
    private val binding: ItemDetailKkiLogResultRecipeBinding,
    detailKkiLogViewModel: DetailKkiLogResultViewModel,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.viewModel = detailKkiLogViewModel
    }

    fun bind(item: Recipe, position: Int) {
        binding.run {
            this.item = item
            this.position = position + 1
            executePendingBindings()
        }
    }
}
