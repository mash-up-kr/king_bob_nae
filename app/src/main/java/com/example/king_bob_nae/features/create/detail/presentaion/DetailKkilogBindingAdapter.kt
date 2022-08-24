package com.example.king_bob_nae.features.create.detail.presentaion

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.king_bob_nae.R

@BindingAdapter("recipeUri")
fun ImageView.bindRecipeImage(recipeUri: Uri?) {
    if (recipeUri == null) {
        this.background = ContextCompat.getDrawable(
            context,
            R.color.gray_background
        )
        this.setImageResource(R.drawable.ic_plus_32)
    } else {
        recipeUri.let {
            Glide.with(this).load(it).into(this)
        }
    }
}

@BindingAdapter("kkiLogImageUri")
fun ImageView.bindKkiLogImage(kkiLogImageUri: Uri?) {
    if (kkiLogImageUri == null) {
        this.background = ContextCompat.getDrawable(
            context,
            R.color.gray_background
        )
    } else {
        kkiLogImageUri.let {
            Glide.with(this).load(it).into(this)
        }
    }
}

@BindingAdapter("setKkiLogImageVisible")
fun View.setKkiLogImageVisible(uri: Uri?) {
    this.isVisible = uri == null
}
