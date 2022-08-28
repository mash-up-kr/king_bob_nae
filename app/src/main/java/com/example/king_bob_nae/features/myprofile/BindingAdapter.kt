package com.example.king_bob_nae.features.myprofile

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.king_bob_nae.R
import com.example.king_bob_nae.features.myprofile.domain.userprofile.UserProfileUiState

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("bindBookMark")
    fun ImageView.bindBookMark(item: UserProfileUiState.ScrapedImage) {
        this.background = if (item.clicked) {
            ContextCompat.getDrawable(this.context, R.drawable.ic_bookmark_on)
        } else {
            ContextCompat.getDrawable(this.context, R.drawable.ic_bookmark_off)
        }
    }

    @JvmStatic
    @BindingAdapter("bindFollow")
    fun ImageView.bindFollow(item: Boolean) {
        if (item) {
            Glide.with(this).load(R.drawable.ic_following_true).into(this)
        } else {
            Glide.with(this).load(R.drawable.ic_following_false).into(this)
        }
    }
}
