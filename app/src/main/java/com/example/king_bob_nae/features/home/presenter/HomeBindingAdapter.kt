package com.example.king_bob_nae.features.home.presenter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bindImage")
fun ImageView.bindImage(url: Int?) {
    Glide.with(this).load(url).into(this)
}
