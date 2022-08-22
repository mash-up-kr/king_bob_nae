package com.example.king_bob_nae.features.myprofile.presentation.follow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.ItemFriendBinding
import com.example.king_bob_nae.features.myprofile.domain.userfollow.UsersFollowUiState

class FollowAdapter : ListAdapter<UsersFollowUiState, FollowAdapter.FollowViewHolder>(followDiffUtil) {
    class FollowViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        ItemFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
    ) {
        private val binding: ItemFriendBinding = DataBindingUtil.bind(itemView) ?: throw IllegalStateException("fail to bind")

        fun bind(item: UsersFollowUiState?) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    companion object {
        private val followDiffUtil = object : DiffUtil.ItemCallback<UsersFollowUiState>() {
            override fun areItemsTheSame(oldItem: UsersFollowUiState, newItem: UsersFollowUiState): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UsersFollowUiState, newItem: UsersFollowUiState): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder {
        return FollowViewHolder(parent)
    }

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
