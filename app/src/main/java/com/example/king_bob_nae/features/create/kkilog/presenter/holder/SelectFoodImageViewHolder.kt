package com.example.king_bob_nae.features.create.kkilog.presenter.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.HolderSelectFoodImageBinding
import com.example.king_bob_nae.features.create.kkilog.data.KkiLogRecipe
import com.example.king_bob_nae.features.create.kkilog.presenter.KkiLogViewModel

class SelectFoodImageViewHolder(
    private val binding: HolderSelectFoodImageBinding,
    private val kkiLogViewModel: KkiLogViewModel,
    private val itemClickListener: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.apply {
            itemView.setOnClickListener {
                itemClickListener()
            }
        }
    }

    fun bind(items: KkiLogRecipe, count: Int) {
        binding.run {
            viewModel = kkiLogViewModel
            executePendingBindings()
        }
    }
}
