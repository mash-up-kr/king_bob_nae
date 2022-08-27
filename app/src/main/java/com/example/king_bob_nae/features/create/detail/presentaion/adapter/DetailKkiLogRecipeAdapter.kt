package com.example.king_bob_nae.features.create.detail.presentaion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.king_bob_nae.R
import com.example.king_bob_nae.features.create.detail.domain.model.KkiLogRecipe
import com.example.king_bob_nae.features.create.detail.presentaion.DetailKkiLogViewModel
import com.example.king_bob_nae.features.create.detail.presentaion.RecipeItemDragListener
import com.example.king_bob_nae.features.create.detail.presentaion.RecipeItemTouchHelperAdapter
import com.example.king_bob_nae.features.create.detail.presentaion.viewholder.RecipeViewHolder
import java.util.*
import javax.inject.Inject

class DetailKkiLogRecipeAdapter @Inject constructor(
    private val detailKkiLogViewModel: DetailKkiLogViewModel,
    private val onItemClick: (KkiLogRecipe) -> Unit,
    val listener: RecipeItemDragListener?
) : ListAdapter<KkiLogRecipe, RecipeViewHolder>(diffCallback), RecipeItemTouchHelperAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_detail_kki_log_recipe,
                parent,
                false
            ),
            detailKkiLogViewModel,
            onItemClick,
            listener
        )
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        val recipeList = this.currentList.toMutableList()
        if (toPosition < 0 || toPosition >= currentList.size) {
            Collections.swap(recipeList, fromPosition, fromPosition)
        } else {
            Collections.swap(recipeList, fromPosition, toPosition)
        }
        detailKkiLogViewModel.updateRecipeList(recipeList)
        return true
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
