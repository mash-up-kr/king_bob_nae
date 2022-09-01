package com.example.king_bob_nae.features.create.detail.presentaion.result.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.ItemDetailKkiLogResultIngredientBinding
import com.example.king_bob_nae.features.create.detail.presentaion.DetailKkiLogViewModel

class ResultIngredientViewHolder(
    private val binding: ItemDetailKkiLogResultIngredientBinding,
    detailKkiLogViewModel: DetailKkiLogViewModel
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.viewModel = detailKkiLogViewModel
    }

    fun bind(item: String) {

        binding.run {
            this.item = item
            executePendingBindings()
        }
    }
}
