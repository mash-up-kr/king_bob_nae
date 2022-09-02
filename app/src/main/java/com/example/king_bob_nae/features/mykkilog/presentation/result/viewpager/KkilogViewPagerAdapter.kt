package com.example.king_bob_nae.features.mykkilog.presentation.result.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.ViewpagerItemBinding

class KkilogViewPagerAdapter : ListAdapter<String, KkilogViewPagerAdapter.ViewPagerViewHolder>(diffUtil) {
    class ViewPagerViewHolder(
        private val parent: ViewGroup,
    ) : RecyclerView.ViewHolder(
        ViewpagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
    ) {
        private val binding: ViewpagerItemBinding = DataBindingUtil.bind(itemView) ?: throw IllegalStateException("fail to bind")
        fun bind(item: String) {
            binding.imageData = item
            binding.executePendingBindings()
        }

    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        return ViewPagerViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
