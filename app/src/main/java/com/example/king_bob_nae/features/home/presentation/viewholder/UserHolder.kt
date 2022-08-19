package com.example.king_bob_nae.features.home.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.HolderFriendBinding
import com.example.king_bob_nae.features.home.domain.freindlist.UserListItem

class UserHolder(
    private val binding: HolderFriendBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: UserListItem) {
        binding.run {
            user = item
            executePendingBindings()
        }
    }
}
