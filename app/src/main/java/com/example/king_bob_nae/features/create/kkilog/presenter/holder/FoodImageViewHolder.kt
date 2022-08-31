package com.example.king_bob_nae.features.create.kkilog.presenter.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.HolderFoodImageBinding
import com.example.king_bob_nae.features.create.kkilog.data.KkiLogRecipe
import com.example.king_bob_nae.features.create.kkilog.presenter.KkiLogViewModel

class FoodImageViewHolder(
    private val binding: HolderFoodImageBinding,
    private val viewModel: KkiLogViewModel
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: KkiLogRecipe) {
        binding.run {
            recipe = item
            ivDelete.setOnClickListener {
                viewModel.removeImage(item)
            }
            executePendingBindings()
        }
    }
}
