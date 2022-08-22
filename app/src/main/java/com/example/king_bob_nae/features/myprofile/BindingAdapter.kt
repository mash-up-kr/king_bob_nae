package com.example.king_bob_nae.features.myprofile

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.king_bob_nae.R
import com.example.king_bob_nae.features.myprofile.domain.UserProfileUiState

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
}
