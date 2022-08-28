package com.example.king_bob_nae.features.myprofile.presentation.follow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.ItemFriendBinding
import com.example.king_bob_nae.features.myprofile.domain.userfollow.UsersFollowUiState
import com.example.king_bob_nae.shared.setOnThrottleClickListener

class FollowAdapter(private val itemClick: (UsersFollowUiState) -> Unit) :
    ListAdapter<UsersFollowUiState, FollowAdapter.FollowViewHolder>(followDiffUtil) {
    class FollowViewHolder(parent: ViewGroup, private val itemClick: (UsersFollowUiState) -> Unit) : RecyclerView.ViewHolder(
        ItemFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
    ) {
        private val binding: ItemFriendBinding = DataBindingUtil.bind(itemView) ?: throw IllegalStateException("fail to bind")
        private lateinit var item: UsersFollowUiState

        init {
            binding.btnFollow.setOnThrottleClickListener {
                itemClick(item)
            }
        }

        fun bind(bindItem: UsersFollowUiState) {
            item = bindItem
            binding.run {
                this.item = bindItem
                executePendingBindings()
            }
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
        return FollowViewHolder(parent, itemClick)
    }

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
