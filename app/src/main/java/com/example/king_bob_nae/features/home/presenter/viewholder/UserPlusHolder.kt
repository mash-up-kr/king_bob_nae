package com.example.king_bob_nae.features.home.presenter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.HolderAddToFriendsBinding

class UserPlusHolder(
    private val binding: HolderAddToFriendsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        binding.run {
            executePendingBindings()
        }
    }
}
