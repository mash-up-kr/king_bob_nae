package com.example.king_bob_nae.features.create.detail.presentaion.write.viewholder

import android.annotation.SuppressLint
import android.text.InputType
import android.view.MotionEvent
import androidx.core.widget.doOnTextChanged
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

        binding.etRecipeDescription.doOnTextChanged { text, _, _, _ ->
            detailKkiLogViewModel.setEmptyDescription(text.toString())
            // TODO updaterecipeDescription 이거 debounce 걸어야하는데
//            if (binding.etRecipeDescription.text.isNotEmpty()) {
//                detailKkiLogViewModel.updateRecipeDescription(item, text.toString())
//            }
        }

        // TODO focus가 이상함 false, true 가 번갈아서 나옴
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
