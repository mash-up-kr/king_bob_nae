package com.example.king_bob_nae.features.create.detail.presentaion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.king_bob_nae.R
import com.example.king_bob_nae.features.create.detail.domain.model.KkiLogIngredient
import com.example.king_bob_nae.features.create.detail.presentaion.DetailKkiLogViewModel
import com.example.king_bob_nae.features.create.detail.presentaion.viewholder.IngredientViewHolder

class DetailKkiLogIngredientAdapter(
    private val detailKkiLogViewModel: DetailKkiLogViewModel
) : ListAdapter<KkiLogIngredient, IngredientViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        return IngredientViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_detail_kki_log_ingredient,
                parent,
                false
            ),
            detailKkiLogViewModel
        )
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<KkiLogIngredient>() {
            override fun areItemsTheSame(oldItem: KkiLogIngredient, newItem: KkiLogIngredient): Boolean =
                oldItem.ingredient == newItem.ingredient

            override fun areContentsTheSame(oldItem: KkiLogIngredient, newItem: KkiLogIngredient): Boolean =
                oldItem == newItem
        }
    }
}
