package com.example.king_bob_nae.features.create.detail.domain.model

import android.net.Uri
import okhttp3.MultipartBody

data class KkiLogRecipe(
    val stepNumber: Int = 0,
    val description: String = "",
    val imageUri: Uri? = null,
    val imageBody: MultipartBody.Part? = null,
    val isEditable: Boolean = false
)
