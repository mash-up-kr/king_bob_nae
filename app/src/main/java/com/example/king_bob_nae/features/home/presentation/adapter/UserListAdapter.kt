package com.example.king_bob_nae.features.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.king_bob_nae.R
import com.example.king_bob_nae.features.home.domain.freindlist.UserListItem
import com.example.king_bob_nae.features.home.presentation.viewholder.UserHolder
import com.example.king_bob_nae.features.home.presentation.viewmodel.HomeViewModel

class UserListAdapter(
    private val viewModel: HomeViewModel
) : ListAdapter<UserListItem, UserHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.holder_friend,
                parent,
                false
            ),
            viewModel
        )
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<UserListItem>() {
            override fun areItemsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean =
                oldItem == newItem
        }
    }
}
