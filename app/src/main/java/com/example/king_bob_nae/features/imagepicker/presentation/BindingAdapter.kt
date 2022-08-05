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
        if (state) {
            alpha = 1.0f
            background = ContextCompat.getDrawable(context, R.drawable.orange_stroke)
        } else {
            alpha = 0.0f
            background = ContextCompat.getDrawable(context, R.drawable.radius_white)
        }
    }

    @JvmStatic
    @BindingAdapter("canTextViewVisible")
    fun TextView.canTextViewVisible(item: ImageState) {
        if (item.clicked) {
            text = item.clickCount.toString()
            background = ContextCompat.getDrawable(context, R.drawable.image_counter_selected)
        } else {
            text = ""
            background = ContextCompat.getDrawable(context, R.drawable.image_counter_not_selected)
        }
    }

    @JvmStatic
    @BindingAdapter("bindUrl")
    fun ImageView.bindUrl(url: String) {
        Glide.with(this)
            .load(url)
            .fitCenter()
            .into(this)
    }
}
