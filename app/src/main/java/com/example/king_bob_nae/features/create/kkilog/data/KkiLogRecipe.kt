package com.example.king_bob_nae.features.create.kkilog.data

import android.os.Parcelable
import com.example.king_bob_nae.features.create.kkilog.presenter.adapter.FOOD_HOLDER
import kotlinx.parcelize.Parcelize

@Parcelize
data class KkiLogRecipe(
    val viewType: Int = FOOD_HOLDER,
    val imageUrl: String = ""
) : Parcelable
