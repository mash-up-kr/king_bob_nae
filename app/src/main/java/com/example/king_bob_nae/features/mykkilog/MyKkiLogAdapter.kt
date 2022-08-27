package com.example.king_bob_nae.features.mykkilog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.R
import com.example.king_bob_nae.databinding.ItemMyKkilogBinding
import com.example.king_bob_nae.features.mykkilog.data.MyKkiLog

class MyKkiLogAdapter : ListAdapter<MyKkiLog, MyKkiLogAdapter.KkilogViewHolder>(diffUtil) {

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
        fun bind(item: MyKkiLog) {
            binding.item = item
            binding.executePendingBindings()
        }
    }


    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<MyKkiLog>() {
            override fun areItemsTheSame(oldItem: MyKkiLog, newItem: MyKkiLog): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: MyKkiLog, newItem: MyKkiLog): Boolean =
                oldItem == newItem

        }
    }
}