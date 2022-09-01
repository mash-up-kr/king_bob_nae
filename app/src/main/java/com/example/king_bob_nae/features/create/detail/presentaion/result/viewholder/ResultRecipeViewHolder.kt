package com.example.king_bob_nae.features.create.detail.presentaion.result.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.ItemDetailKkiLogResultRecipeBinding
import com.example.king_bob_nae.features.create.detail.data.Recipe
import com.example.king_bob_nae.features.create.detail.presentaion.DetailKkiLogViewModel

class ResultRecipeViewHolder(
    private val binding: ItemDetailKkiLogResultRecipeBinding,
    detailKkiLogViewModel: DetailKkiLogViewModel,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.viewModel = detailKkiLogViewModel
    }

    fun bind(item: Recipe) {
        binding.run {
            this.item = item
            executePendingBindings()
        }
    }
}
