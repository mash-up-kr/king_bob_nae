package com.example.king_bob_nae.features.myprofile.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.MyProfileRecyclerviewItemBinding

class MyProfileAdapter : ListAdapter<MyProfile, MyProfileAdapter.MyProfileViewHolder>(profileDiffUtil) {
    class MyProfileViewHolder(private val parent: ViewGroup) : RecyclerView.ViewHolder(
        MyProfileRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
    ) {
        private val binding: MyProfileRecyclerviewItemBinding = DataBindingUtil.bind(itemView) ?: throw IllegalStateException("fail to bind")
        fun bind(item: MyProfile) {
            binding.run {
                myprofile = item
                executePendingBindings()
            }
        }

    }

    companion object {
        private val profileDiffUtil = object : DiffUtil.ItemCallback<MyProfile>() {
            override fun areItemsTheSame(oldItem: MyProfile, newItem: MyProfile): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MyProfile, newItem: MyProfile): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProfileViewHolder {
        return MyProfileViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MyProfileViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
