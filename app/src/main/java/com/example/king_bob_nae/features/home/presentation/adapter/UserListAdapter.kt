package com.example.king_bob_nae.features.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.R
import com.example.king_bob_nae.features.home.domain.UserListItem
import com.example.king_bob_nae.features.home.presentation.viewholder.UserHolder
import com.example.king_bob_nae.features.home.presentation.viewholder.UserPlusHolder

class UserListAdapter : ListAdapter<UserListItem, RecyclerView.ViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_USER -> UserHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.holder_friend,
                    parent,
                    false
                )
            )
            TYPE_PLUS -> UserPlusHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.holder_add_to_friends,
                    parent,
                    false
                )
            )
            else -> throw TypeCastException("is undefined view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserHolder -> {
                (getItem(position) as? UserListItem.User)?.let {
                    holder.bind(it)
                }
            }
            is UserPlusHolder -> holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is UserListItem.User -> TYPE_USER
            is UserListItem.Plus -> TYPE_PLUS
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<UserListItem>() {
            override fun areItemsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean =
                when {
                    oldItem is UserListItem.User && newItem is UserListItem.User &&
                        oldItem.id == newItem.id -> true
                    oldItem is UserListItem.Plus && newItem is UserListItem.Plus
                    -> true
                    else -> false
                }

            override fun areContentsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean =
                oldItem == newItem
        }
        const val TYPE_USER = 1
        const val TYPE_PLUS = 2
    }
}
