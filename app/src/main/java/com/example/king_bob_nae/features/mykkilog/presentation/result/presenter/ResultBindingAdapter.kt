package com.example.king_bob_nae.features.mykkilog.presentation.result.presenter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.king_bob_nae.R
import com.example.king_bob_nae.features.mykkilog.domain.getkkilog.KkilogResultUiState

@BindingAdapter("bindLike")
fun ImageView.bindLike(clicked: Boolean) {
    if (clicked) {
        Glide.with(this).load(R.drawable.ic_like_able).into(this)
    } else {
        Glide.with(this).load(R.drawable.ic_like_disable_48).into(this)
    }
}

@BindingAdapter("bindLikeText")
fun TextView.bindLikeText(likeCount: Int) {
    text = if (likeCount == 0) {
        "좋아요"
    } else {
        "좋아요 ${likeCount}개"
    }
}

@BindingAdapter("bindKkilogBookMark")
fun ImageView.bindKkilogBookMark(kkilog: KkilogResultUiState?) {
    kkilog?.let {
        if (kkilog.isScraped) {
            Glide.with(this).load(R.drawable.ic_bookmark_active_48).into(this)
        } else {
            Glide.with(this).load(R.drawable.ic_bookmark_disable_48).into(this)
        }
    }
}

@BindingAdapter("bindDate")
fun TextView.bindDate(date: String?) {
    date?.let {
        text = date.dropLast(15)
    }
}
