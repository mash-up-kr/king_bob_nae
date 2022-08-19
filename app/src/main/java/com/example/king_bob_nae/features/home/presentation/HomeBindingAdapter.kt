package com.example.king_bob_nae.features.home.presentation

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bindImage")
fun ImageView.bindImage(url: String?) {
    url?.let {
        Glide.with(this).load(it).into(this)
    }
}
