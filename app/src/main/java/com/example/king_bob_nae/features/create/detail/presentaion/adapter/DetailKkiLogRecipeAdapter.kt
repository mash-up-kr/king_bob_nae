package com.example.king_bob_nae.features.create.detail.presentaion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.king_bob_nae.R
import com.example.king_bob_nae.features.create.detail.domain.KkiLogRecipe
import com.example.king_bob_nae.features.create.detail.presentaion.DetailKkiLogViewModel
import com.example.king_bob_nae.features.create.detail.presentaion.viewholder.RecipeViewHolder

class DetailKkiLogRecipeAdapter(
    private val detailKkiLogViewModel: DetailKkiLogViewModel,
    private val onItemClick: (KkiLogRecipe) -> Unit
) : ListAdapter<KkiLogRecipe, RecipeViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_detail_kki_log_recipe,
                parent,
                false
            ),
            detailKkiLogViewModel,
            onItemClick
        )
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<KkiLogRecipe>() {
            override fun areItemsTheSame(oldItem: KkiLogRecipe, newItem: KkiLogRecipe): Boolean =
                oldItem.stepNumber == newItem.stepNumber

            override fun areContentsTheSame(oldItem: KkiLogRecipe, newItem: KkiLogRecipe): Boolean =
                oldItem == newItem
        }
    }
}
