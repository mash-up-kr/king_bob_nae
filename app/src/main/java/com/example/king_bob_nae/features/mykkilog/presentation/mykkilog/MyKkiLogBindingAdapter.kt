package com.example.king_bob_nae.features.mykkilog.presentation.mykkilog

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


@BindingAdapter(value = ["imageUrl", "isSimple"], requireAll = true)
fun ImageView.MyKkiLogBinding(imageUrl: String?, isSimple: Boolean) {
    imageUrl?.let {
        if (isSimple)
            Glide.with(this).load(imageUrl).circleCrop().into(this)
        else
            Glide.with(this).load(imageUrl)
                .transform(CenterCrop(), RoundedCorners(20)).into(this)
    }
}
