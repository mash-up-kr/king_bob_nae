package com.example.king_bob_nae.features.imagepicker.presentation

data class ImageState(
    val clicked: Boolean = false,
    var clickCount: Int = 1,
    val imageUrl: String
)
