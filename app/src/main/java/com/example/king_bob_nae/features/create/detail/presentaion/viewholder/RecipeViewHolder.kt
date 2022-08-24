package com.example.king_bob_nae.features.create.detail.presentaion.viewholder

import android.text.InputType
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.ItemDetailKkiLogRecipeBinding
import com.example.king_bob_nae.features.create.detail.domain.KkiLogRecipe
import com.example.king_bob_nae.features.create.detail.presentaion.DetailKkiLogViewModel

class RecipeViewHolder(
    private val binding: ItemDetailKkiLogRecipeBinding,
    private val detailKkiLogViewModel: DetailKkiLogViewModel,
    private val onItemClick: (KkiLogRecipe) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var item: KkiLogRecipe

    init {
        binding.viewModel = detailKkiLogViewModel
        binding.ivFoodImage.setOnClickListener {
            onItemClick(item)
        }

        binding.etRecipeDescription.doOnTextChanged { text, _, _, _ ->
            detailKkiLogViewModel.emptyDescription.value = text.toString()
        }

        binding.etRecipeDescription.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (binding.etRecipeDescription.text.isEmpty()) {
                    detailKkiLogViewModel.updateRecipeDescription(item)
                }
                binding.etRecipeDescription.apply {
                    clearFocus()
                    isFocusable = false
                    isEnabled = false
                    inputType = InputType.TYPE_NULL
                }
            }
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
