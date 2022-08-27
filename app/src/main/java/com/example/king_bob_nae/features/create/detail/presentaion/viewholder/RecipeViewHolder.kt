package com.example.king_bob_nae.features.create.detail.presentaion.viewholder

import android.annotation.SuppressLint
import android.text.InputType
import android.view.MotionEvent
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.ItemDetailKkiLogRecipeBinding
import com.example.king_bob_nae.features.create.detail.domain.model.KkiLogRecipe
import com.example.king_bob_nae.features.create.detail.presentaion.DetailKkiLogViewModel
import com.example.king_bob_nae.features.create.detail.presentaion.RecipeItemDragListener

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
            detailKkiLogViewModel.setEmptyDescription(it.toString())
        }

        binding.etRecipeDescription.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (binding.etRecipeDescription.text.isNotEmpty()) {
                    detailKkiLogViewModel.updateRecipeDescription(item)
                    binding.etRecipeDescription.apply {
                        clearFocus()
                        isEnabled = false
                        inputType = InputType.TYPE_NULL
                    }
                }
            }
        }
        binding.ivReorder.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                listener?.onStartDrag(this@RecipeViewHolder)
            }
            false
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
