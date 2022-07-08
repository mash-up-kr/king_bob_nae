package com.example.king_bob_nae.features.imagepicker.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.ItemImageBinding

class ImageListAdapter(private val clickedItem: (ImageState) -> Unit) : ListAdapter<ImageState, ImageListAdapter.ImageViewHolder>(diffUtil) {
    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<ImageState>() {
            override fun areItemsTheSame(oldItem: ImageState, newItem: ImageState): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ImageState, newItem: ImageState): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(parent, clickedItem)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ImageViewHolder(private val parent: ViewGroup, private val clickedItem: (ImageState) -> Unit) : RecyclerView.ViewHolder(
        ItemImageBinding.inflate(LayoutInflater.from(parent.context)).root
    ) {
        private lateinit var imageState: ImageState

        init {
            itemView.setOnClickListener {
                clickedItem(imageState)
            }
        }

        private val binding: ItemImageBinding = DataBindingUtil.bind(itemView) ?: throw IllegalStateException("fail to bind")
        fun bind(item: ImageState) {
            imageState = item
            binding.run {
                imageState = item
                executePendingBindings()
            }
        }
    }


}
