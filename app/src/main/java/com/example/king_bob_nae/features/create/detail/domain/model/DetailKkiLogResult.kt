package com.example.king_bob_nae.features.create.detail.domain.model

import androidx.annotation.Keep
import com.example.king_bob_nae.features.create.detail.data.KkiLogImage
import com.example.king_bob_nae.features.create.detail.data.Like
import com.example.king_bob_nae.features.create.detail.data.Recipe
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class DetailKkiLogResult(
    val createdAt: String = "",
    val description: String = "",
    val id: Int = 0,
    val image: KkiLogImage? = null,
    val ingredients: List<String>? = emptyList(),
    val isScrapped: Boolean = false,
    val like: Like? = null,
    val recipes: List<Recipe> = emptyList(),
    val title: String = "",
    val updatedAt: String = ""
)
