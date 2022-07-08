package com.example.king_bob_nae.features.imagepicker.presentation

import android.net.Uri

data class ImageState(
    val id: Long,
    val clicked: Boolean = false,
    val clickCount: Int = 1,
    val imageUrl: String
)
