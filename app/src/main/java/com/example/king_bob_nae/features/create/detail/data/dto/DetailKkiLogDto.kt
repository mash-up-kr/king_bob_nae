package com.example.king_bob_nae.features.create.detail.data

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class DetailKkiLogDto(
    @SerialName("data")
    val data: DetailKkiLog,
    @SerialName("error")
    val error: String?
)

@Keep
@Serializable
data class DetailKkiLog(
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("description")
    val description: String,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: KkiLogImage,
    @SerialName("ingredients")
    val ingredients: List<String>,
    @SerialName("isScrapped")
    val isScrapped: Boolean,
    @SerialName("like")
    val like: Like,
    @SerialName("recipes")
    val recipes: List<Recipe>,
    @SerialName("title")
    val title: String,
    @SerialName("updatedAt")
    val updatedAt: String
)

@Keep
@Serializable
data class KkiLogImage(
    @SerialName("original")
    val original: String,
    @SerialName("w1024")
    val w1024: String,
    @SerialName("w256")
    val w256: String
)

@Keep
@Serializable
data class Like(
    @SerialName("count")
    val count: Int,
    @SerialName("isLike")
    val isLike: Boolean
)

@Keep
@Serializable
data class Recipe(
    @SerialName("description")
    val description: String,
    @SerialName("image")
    val image: KkiLogImage
)
