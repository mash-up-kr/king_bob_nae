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
        alpha = if (state) 1.0f else 0.0f
        background = ContextCompat.getDrawable(
            context,
            if (state) R.drawable.orange_stroke else R.drawable.radius_white
        )
    }

    @JvmStatic
    @BindingAdapter("canTextViewVisible")
    fun TextView.canTextViewVisible(item: ImageState) {
        text = if (item.clicked) item.clickCount.toString() else ""
        background = ContextCompat.getDrawable(
            context,
            if (item.clicked) R.drawable.image_counter_selected else R.drawable.image_counter_not_selected
        )
    }

    @JvmStatic
    @BindingAdapter("bindUrl")
    fun ImageView.bindUrl(url: String?) {
        url?.let {
            Glide.with(this)
                .load(it)
                .fitCenter()
                .into(this)
        }
    }
}
