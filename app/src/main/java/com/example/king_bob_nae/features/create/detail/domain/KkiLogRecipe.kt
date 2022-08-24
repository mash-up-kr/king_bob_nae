package com.example.king_bob_nae.features.create.detail.domain

import android.net.Uri

data class KkiLogRecipe(
    val stepNumber: Int = 0,
    val description: String = "",
    val imageUri: Uri? = null
)
