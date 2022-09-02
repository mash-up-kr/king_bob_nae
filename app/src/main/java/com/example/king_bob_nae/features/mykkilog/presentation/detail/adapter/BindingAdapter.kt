package com.example.king_bob_nae.features.mykkilog.presentation.detail.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.king_bob_nae.features.create.detail.presentaion.DetailKkiLogSharedViewModel


@BindingAdapter("bindStep")
fun TextView.bindStep(step: Int) {
    text = "STEP. $step"
}

@BindingAdapter("bindPosition", "bindTotal")
fun TextView.bindPosition(position: Int, viewModel: DetailKkiLogSharedViewModel) {
    val total = viewModel.recipePair.value.second.count()
    text = "$position/$total"
}
