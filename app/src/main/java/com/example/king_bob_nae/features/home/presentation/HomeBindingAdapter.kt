package com.example.king_bob_nae.features.home.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.king_bob_nae.features.home.domain.userstate.HomeUserState

@BindingAdapter("bindImage")
fun ImageView.bindImage(url: String?) {
    url?.let {
        Glide.with(this).load(it).into(this)
    }
}

@BindingAdapter("bindCircleImage")
fun ImageView.bindCircleImage(url: String?) {
    url?.let {
        Glide.with(this).load(it).transform(CenterCrop(), RoundedCorners(20)).into(this)
    }
}

@BindingAdapter("bindHomeImage")
fun ImageView.bindHomeImage(home: HomeUserState?) {
    home?.let {
        if (home.progressBar < home.max) {
            // 대파 이미지 보여주기
            if (home.level != "Lv.3") {
                visibility = View.VISIBLE
                home.largeImageUrl.let {
                    Glide.with(this).load(it).into(this)
                }
            } else {
                // do nothing
            }
        } else {
            if (home.level == "Lv.3" || home.isFriend) {
                visibility = View.VISIBLE
                home.largeImageUrl.let {
                    Glide.with(this).load(it).into(this)
                }
            } else {
                visibility = View.GONE
            }
        }
    }
}

@BindingAdapter("clickTextVisible")
fun TextView.clickTextVisible(home: HomeUserState?) {
    home?.let {
        visibility = if (home.progressBar >= home.max) {
            if (home.level != "Lv.3" && !home.isFriend) {
                View.VISIBLE
            } else {
                View.GONE
            }
        } else {
            View.GONE
        }
    }
}

@BindingAdapter("bindLottie")
fun LottieAnimationView.bindLottie(home: HomeUserState?) {
    home?.let {
        visibility = if (home.progressBar >= home.max) {
            if (home.level != "Lv.3" && !home.isFriend) {
                View.VISIBLE
            } else {
                View.GONE
            }
        } else {
            View.GONE
        }
    }
}
