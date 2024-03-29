package com.example.king_bob_nae.features.mykkilog.data.patchkkilog


import com.example.king_bob_nae.features.intro.data.dto.ErrorDto
import com.google.gson.annotations.SerializedName

data class PatchKkilogResult(
    @SerializedName("error")
    val error: ErrorDto?,
    @SerializedName("data")
    val data: PatchKkilog,
)

data class PatchKkilog(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("cursor")
    val cursor: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("isScrapped")
    val isScrapped: Boolean,
    @SerializedName("kick")
    val kick: String,
    @SerializedName("like")
    val like: Like,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
) {
    data class Image(
        @SerializedName("original")
        val original: String,
        @SerializedName("w1024")
        val w1024: String,
        @SerializedName("w256")
        val w256: String,
    )

    data class Like(
        @SerializedName("count")
        val count: Int,
        @SerializedName("isLike")
        val isLike: Boolean,
    )
}
