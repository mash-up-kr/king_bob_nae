package com.example.king_bob_nae.features.create.kkilog.data.dto

import androidx.annotation.Keep
import com.example.king_bob_nae.features.intro.data.dto.ErrorDto
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class UpLoadResponse(
    val data: UpLoadData?,
    val error: ErrorDto
)

@Serializable
data class UpLoadData(
    val createdAt: String, // string
    val cursor: String, // string
    val description: String, // string
    val id: Int, // 0
    val image: Image,
    val ingredients: List<String>,
    val isScrapped: Boolean, // true
    val like: Like,
    val recipes: List<Recipe>,
    val title: String, // string
    val updatedAt: String // string
) {
    @Serializable
    data class Image(
        val original: String, // string
        val w1024: String, // string
        val w256: String // string
    )
    @Serializable
    data class Like(
        val count: Int, // 0
        val isLike: Boolean // true
    )
    @Serializable
    data class Recipe(
        val description: String, // string
        val image: Image
    ) {
        @Serializable
        data class Image(
            val original: String, // string
            val w1024: String, // string
            val w256: String // string
        )
    }
}