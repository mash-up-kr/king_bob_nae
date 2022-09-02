package com.example.king_bob_nae.features.create.detail.presentaion.write.viewholder

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.ItemDetailKkiLogRecipeBinding
import com.example.king_bob_nae.features.create.detail.domain.model.KkiLogRecipe
import com.example.king_bob_nae.features.create.detail.presentaion.DetailKkiLogViewModel
import com.example.king_bob_nae.features.create.detail.presentaion.write.RecipeItemDragListener

@SuppressLint("ClickableViewAccessibility")
class RecipeViewHolder(
    private val binding: ItemDetailKkiLogRecipeBinding,
    private val detailKkiLogViewModel: DetailKkiLogViewModel,
    private val onItemClick: (KkiLogRecipe) -> Unit,
    val listener: RecipeItemDragListener?
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var item: KkiLogRecipe

    init {
        binding.viewModel = detailKkiLogViewModel
        binding.ivFoodImage.setOnClickListener {
            onItemClick(item)
        }

        binding.etRecipeDescription.doAfterTextChanged {
            if (binding.etRecipeDescription.text.isNotEmpty()) {
                detailKkiLogViewModel.updateRecipeDescription(item, it.toString())
            }
        }

        binding.ivReorder.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                listener?.onStartDrag(this@RecipeViewHolder)
            }
            false
        }

        binding.etRecipeDescription.viewTreeObserver.addOnGlobalLayoutListener {
            binding.etRecipeDescription.setSelection(binding.etRecipeDescription.length())
        }
    }

    fun bind(item: KkiLogRecipe) {
        this.item = item

        binding.run {
            this.item = item
            executePendingBindings()
        }
    }
}
