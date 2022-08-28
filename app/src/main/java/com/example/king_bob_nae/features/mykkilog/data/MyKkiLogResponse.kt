package com.example.king_bob_nae.features.mykkilog.data

import kotlinx.serialization.Serializable

@Serializable
data class MyKkiLogResponse(
    val id: Int,
    val image: Image,
    val createdAt: String,
    val title: String
) {
    @Serializable
    data class Image(
        val w56: String
    )
}