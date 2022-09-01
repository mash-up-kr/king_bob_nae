package com.example.king_bob_nae.features.create.detail.presentaion.write.viewholder

import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.databinding.ItemDetailKkiLogIngredientBinding
import com.example.king_bob_nae.features.create.detail.domain.model.KkiLogIngredient
import com.example.king_bob_nae.features.create.detail.presentaion.DetailKkiLogViewModel

class IngredientViewHolder(
    val binding: ItemDetailKkiLogIngredientBinding,
    private val detailKkiLogViewModel: DetailKkiLogViewModel
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var item: KkiLogIngredient

    init {
        binding.viewModel = detailKkiLogViewModel

        binding.etIngredient.doAfterTextChanged {
//            detailKkiLogViewModel.setEmptyIngredient(it.toString())
            if (binding.etIngredient.text.isNotEmpty()) {
                detailKkiLogViewModel.updateIngredient(item, it.toString())
            }
        }

//        binding.etIngredient.setOnFocusChangeListener { _, hasFocus ->
//            if (!hasFocus) {
//                if (binding.etIngredient.text.isNotEmpty()) {
//                    detailKkiLogViewModel.updateIngredient(item)
//                    binding.etIngredient.apply {
//                        clearFocus()
//                        isEnabled = false
//                        inputType = InputType.TYPE_NULL
//                    }
//                }
//            }
//        }

        binding.etIngredient.viewTreeObserver.addOnGlobalLayoutListener {
            binding.etIngredient.setSelection(binding.etIngredient.length())
        }
    }

    fun bind(item: KkiLogIngredient) {
        this.item = item

        binding.run {
            this.item = item
            executePendingBindings()
        }
    }
}
