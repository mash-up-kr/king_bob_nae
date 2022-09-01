package com.example.king_bob_nae.features.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.ItemAllKkiLogBinding
import com.example.king_bob_nae.features.home.domain.friendshome.KkilogState
import com.example.king_bob_nae.shared.setOnThrottleClickListener

class AllKkilogAdapter(private val itemClick: (KkilogState) -> Unit) : ListAdapter<KkilogState, AllKkilogAdapter.AllKkilogViewHolder>(diffCallback) {
    class AllKkilogViewHolder(parent: ViewGroup, private val itemClick: (KkilogState) -> Unit) : RecyclerView.ViewHolder(
        ItemAllKkiLogBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
    ) {
        lateinit var item: KkilogState
        private val binding: ItemAllKkiLogBinding = DataBindingUtil.bind(itemView) ?: throw IllegalStateException("fail to bind")

        init {
            itemView.setOnThrottleClickListener {
                itemClick(item)
            }
        }

        fun bind(item: KkilogState) {
            this.item = item
            binding.kkilog = item
            binding.executePendingBindings()
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<KkilogState>() {
            override fun areItemsTheSame(oldItem: KkilogState, newItem: KkilogState): Boolean {
                return oldItem.imageUrl == newItem.imageUrl
            }

            override fun areContentsTheSame(oldItem: KkilogState, newItem: KkilogState): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllKkilogViewHolder {
        return AllKkilogViewHolder(parent, itemClick)
    }

    override fun onBindViewHolder(holder: AllKkilogViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
