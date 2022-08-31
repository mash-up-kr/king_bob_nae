package com.example.king_bob_nae.features.create.kkilog.presenter.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.king_bob_nae.features.create.kkilog.data.KkiLogRecipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


@BindingAdapter("bindFood")
fun ImageView.bindFood(imageUri: String) {
    imageUri.let {
        Glide.with(this).load(imageUri)
            .transform(CenterCrop(), RoundedCorners(20)).into(this)
    }
}

@BindingAdapter("bindCount")
fun TextView.bindCount(list: StateFlow<List<KkiLogRecipe>>?) {
    list?.let {
        CoroutineScope(Dispatchers.Main).launch {
            list.collect {
                text = "${it.count() - 1}/10"
            }
        }
    }
}