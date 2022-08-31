package com.example.king_bob_nae.features.create.detail.presentaion.write.viewholder

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.king_bob_nae.R

@BindingAdapter("setIsScrapped")
fun ImageView.setIsScrapped(isScrapped: Boolean) {
    background = ContextCompat.getDrawable(
        context,
        if (isScrapped) R.drawable.ic_bookmark_active_48 else R.drawable.ic_bookmark_disable_48
    )
}

@BindingAdapter("setIsLiked")
fun ImageView.setIsLiked(isLiked: Boolean) {
    background = ContextCompat.getDrawable(
        context,
        if (isLiked) R.drawable.ic_like_disable_48 else R.drawable.ic_like_disable_48 // TODO 여기 하트 이미지 바꾸기
    )
}
