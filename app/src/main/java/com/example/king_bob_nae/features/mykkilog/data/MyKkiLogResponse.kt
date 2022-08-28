package com.example.king_bob_nae.features.mykkilog.data

data class MyKkiLogThumbNail(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val data: String,
    var isSimple: Boolean = true
)