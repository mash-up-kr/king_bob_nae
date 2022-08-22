package com.example.king_bob_nae.features.myprofile.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.MyProfileRecyclerviewItemBinding
import com.example.king_bob_nae.features.myprofile.domain.UserProfileUiState.ScrapedImage
import com.example.king_bob_nae.shared.setOnThrottleClickListener

class MyProfileAdapter(private val itemClick: (ScrapedImage) -> Unit) :
    ListAdapter<ScrapedImage, MyProfileAdapter.MyProfileViewHolder>(profileDiffUtil) {
    class MyProfileViewHolder(
        parent: ViewGroup,
        private val itemClick: (ScrapedImage) -> Unit,
    ) : RecyclerView.ViewHolder(
        MyProfileRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
    ) {
        private val binding: MyProfileRecyclerviewItemBinding = DataBindingUtil.bind(itemView) ?: throw IllegalStateException("fail to bind")
        private lateinit var item: ScrapedImage

        init {
            with(binding) {
                ivMyProfileBookmark.setOnThrottleClickListener {
                    itemClick(item)
                }
            }
        }

        fun bind(item: ScrapedImage) {
            this.item = item
            binding.run {
                scrapedimage = item
                executePendingBindings()
            }
        }

    }

    companion object {
        private val profileDiffUtil = object : DiffUtil.ItemCallback<ScrapedImage>() {
            override fun areItemsTheSame(oldItem: ScrapedImage, newItem: ScrapedImage): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ScrapedImage, newItem: ScrapedImage): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProfileViewHolder {
        return MyProfileViewHolder(parent, itemClick)
    }

    override fun onBindViewHolder(holder: MyProfileViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
