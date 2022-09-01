package com.example.king_bob_nae.features.create.detail.presentaion.result.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.king_bob_nae.R
import com.example.king_bob_nae.features.create.detail.data.Recipe
import com.example.king_bob_nae.features.create.detail.presentaion.DetailKkiLogViewModel
import com.example.king_bob_nae.features.create.detail.presentaion.result.viewholder.ResultRecipeViewHolder

class ResultRecipeAdapter(
    private val detailKkiLogViewModel: DetailKkiLogViewModel
) : ListAdapter<Recipe, ResultRecipeViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultRecipeViewHolder {
        return ResultRecipeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_detail_kki_log_result_recipe,
                parent,
                false
            ),
            detailKkiLogViewModel
        )
    }

    override fun onBindViewHolder(holder: ResultRecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(
                oldItem: Recipe,
                newItem: Recipe
            ): Boolean =
                oldItem.image == newItem.image

            override fun areContentsTheSame(
                oldItem: Recipe,
                newItem: Recipe
            ): Boolean =
                oldItem == newItem
        }
    }
}
