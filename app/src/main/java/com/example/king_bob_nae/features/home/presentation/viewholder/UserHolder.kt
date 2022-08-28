package com.example.king_bob_nae.features.home.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.HolderFriendBinding
import com.example.king_bob_nae.features.home.domain.freindlist.UserListItem
import com.example.king_bob_nae.features.home.presentation.viewmodel.HomeViewModel

class UserHolder(
    private val binding: HolderFriendBinding,
    homeViewModel: HomeViewModel
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.viewModel = homeViewModel
    }

    fun bind(item: UserListItem) {
        binding.run {
            user = item
            executePendingBindings()
        }
    }
}
