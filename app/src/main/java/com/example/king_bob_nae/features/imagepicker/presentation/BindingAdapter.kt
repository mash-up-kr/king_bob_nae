package com.example.king_bob_nae.features.imagepicker.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.king_bob_nae.R

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("bindBackground")
    fun View.bindBackground(state: Boolean) {
        background = if (state) ContextCompat.getDrawable(
            this.context,
            R.drawable.orange_stroke
        ) else ContextCompat.getDrawable(this.context, R.drawable.radius_gray)
    }

    @JvmStatic
    @BindingAdapter("canTextViewVisible")
    fun TextView.canTextViewVisible(item: ImageState) {
        background = if (item.clicked) {
            ContextCompat.getDrawable(this.context, R.drawable.radius_orange)
        } else {
            ContextCompat.getDrawable(this.context, R.drawable.radius_gray)
        }
    }

    @JvmStatic
    @BindingAdapter("bindUrl")
    fun ImageView.bindUrl(url: String) {
        Glide.with(this)
            .load(url)
            .into(this)
    }
}
