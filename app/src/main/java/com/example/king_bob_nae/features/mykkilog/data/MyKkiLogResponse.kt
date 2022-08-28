package com.example.king_bob_nae.features.mykkilog.data

import com.example.king_bob_nae.features.intro.data.dto.ErrorDto
import kotlinx.serialization.Serializable


data class MyKkiLogResponse(
    val data: List<KkiLogList>?,
    val error: ErrorDto?
)

@Serializable
data class KkiLogList(
    val id: Int,
    val image: Image,
    val createdAt: String,
    val title: String
) {
    @Serializable
    data class Image(
        val w256: String
    )
}