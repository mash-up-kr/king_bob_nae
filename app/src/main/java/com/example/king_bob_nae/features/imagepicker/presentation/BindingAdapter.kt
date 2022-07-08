package com.example.king_bob_nae.features.imagepicker.presentation

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("canVisible")
    fun View.canVisible(item: ImageState) {
        if (item.clicked) visibility = View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("canVisible")
    fun TextView.canVisible(item: ImageState) {
        if (item.clicked) visibility = View.VISIBLE else View.GONE
        text = item.clickCount.toString()
    }

    @JvmStatic
    @BindingAdapter("bindUrl")
    fun ImageView.bindUrl(url: String) {
        Glide.with(this)
            .load(url)
            .into(this)
    }
}
