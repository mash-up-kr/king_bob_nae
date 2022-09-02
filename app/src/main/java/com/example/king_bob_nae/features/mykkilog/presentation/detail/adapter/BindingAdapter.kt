package com.example.king_bob_nae.features.mykkilog.presentation.detail.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("bindStep")
fun TextView.bindStep(step: Int) {
    text = "STEP. $step"
}