package com.example.king_bob_nae.features.create.detail.presentaion.result.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.king_bob_nae.R
import com.example.king_bob_nae.features.create.detail.presentaion.result.DetailKkiLogResultViewModel
import com.example.king_bob_nae.features.create.detail.presentaion.result.viewholder.ResultIngredientViewHolder

class ResultIngredientAdapter(
    private val detailKkiLogResultViewModel: DetailKkiLogResultViewModel
) : ListAdapter<String, ResultIngredientViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultIngredientViewHolder {
        return ResultIngredientViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_detail_kki_log_result_ingredient,
                parent,
                false
            ),
            detailKkiLogResultViewModel
        )
    }

    override fun onBindViewHolder(holder: ResultIngredientViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean =
                oldItem === newItem

            override fun areContentsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean =
                oldItem == newItem
        }
    }
}
