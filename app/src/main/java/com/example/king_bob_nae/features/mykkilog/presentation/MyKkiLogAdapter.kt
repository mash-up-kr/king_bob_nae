package com.example.king_bob_nae.features.mykkilog.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.R
import com.example.king_bob_nae.databinding.ItemMyKkilogBinding
import com.example.king_bob_nae.features.mykkilog.data.MyKkiLogThumbNail

class MyKkiLogAdapter : ListAdapter<MyKkiLogThumbNail, MyKkiLogAdapter.KkilogViewHolder>(diffUtil) {

    override fun onBindViewHolder(holder: KkilogViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KkilogViewHolder {
        return KkilogViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_my_kkilog,
                parent,
                false
            )
        )
    }

    class KkilogViewHolder(
        private val binding: ItemMyKkilogBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyKkiLogThumbNail) {
            binding.item = item
            binding.executePendingBindings()
        }
    }


    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<MyKkiLogThumbNail>() {
            override fun areItemsTheSame(
                oldItem: MyKkiLogThumbNail,
                newItem: MyKkiLogThumbNail
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MyKkiLogThumbNail,
                newItem: MyKkiLogThumbNail
            ): Boolean =
                oldItem == newItem

        }
    }
}