package com.example.king_bob_nae.features.create.detail.presentaion

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.king_bob_nae.R
import com.example.king_bob_nae.features.create.detail.data.Recipe
import com.example.king_bob_nae.features.create.detail.presentaion.result.adapter.ResultIngredientAdapter
import com.example.king_bob_nae.features.create.detail.presentaion.result.adapter.ResultRecipeAdapter

@BindingAdapter("recipeUri")
fun ImageView.bindRecipeImage(recipeUri: Uri?) {
    if (recipeUri == null) {
        this.background = ContextCompat.getDrawable(
            context,
            R.color.gray_background
        )
    } else {
        recipeUri.let {
            Glide.with(this).load(it).fitCenter().into(this)
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
            Glide.with(this).load(it).fitCenter().into(this)
        }
    }
}

@BindingAdapter("setKkiLogImageVisible")
fun View.setKkiLogImageVisible(uri: Uri?) {
    this.isVisible = uri == null
}

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

@BindingAdapter("setIngredientList")
fun RecyclerView.setIngredientList(list: List<String>?) {

    list?.let {
        (adapter as? ResultIngredientAdapter)?.submitList(it)
    }
}

@BindingAdapter("setRecipeList")
fun RecyclerView.setRecipeList(list: List<Recipe>?) {

    list?.let {
        (adapter as? ResultRecipeAdapter)?.submitList(it)
    }
}
