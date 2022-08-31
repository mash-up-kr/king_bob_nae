package com.example.king_bob_nae.features.create.kkilog.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.R
import com.example.king_bob_nae.features.create.kkilog.data.KkiLogRecipe
import com.example.king_bob_nae.features.create.kkilog.presenter.KkiLogViewModel
import com.example.king_bob_nae.features.create.kkilog.presenter.holder.FoodImageViewHolder
import com.example.king_bob_nae.features.create.kkilog.presenter.holder.SelectFoodImageViewHolder

const val FIRST_HOLDER = 1
const val FOOD_HOLDER = 2

class FoodListAdapter(
    private val viewModel: KkiLogViewModel,
    private val itemClickListener: () -> Unit
) : ListAdapter<KkiLogRecipe, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            FIRST_HOLDER -> {
                return SelectFoodImageViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.holder_select_food_image,
                        parent,
                        false
                    ),
                    viewModel,
                    itemClickListener
                )
            }
            else -> {
                return FoodImageViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.holder_food_image,
                        parent,
                        false
                    ),
                    viewModel
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItem(position).viewType) {
            FIRST_HOLDER -> {
                (holder as SelectFoodImageViewHolder).bind(getItem(position), itemCount)
            }
            FOOD_HOLDER -> {
                (holder as FoodImageViewHolder).bind(getItem(position))
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<KkiLogRecipe>() {
            override fun areItemsTheSame(oldItem: KkiLogRecipe, newItem: KkiLogRecipe): Boolean =
                oldItem.imageUrl == newItem.imageUrl

            override fun areContentsTheSame(oldItem: KkiLogRecipe, newItem: KkiLogRecipe): Boolean =
                oldItem == newItem
        }
    }
}
