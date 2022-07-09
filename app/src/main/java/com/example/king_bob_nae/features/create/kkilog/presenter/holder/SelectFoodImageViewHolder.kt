package com.example.king_bob_nae.features.create.kkilog.presenter.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.HolderSelectFoodImageBinding

class SelectFoodImageViewHolder(
    private val binding: HolderSelectFoodImageBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        binding.run {
            executePendingBindings()
        }
    }
}
