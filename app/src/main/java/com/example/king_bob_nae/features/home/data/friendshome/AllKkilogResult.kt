package com.example.king_bob_nae.features.home.data.friendshome

import com.example.king_bob_nae.features.intro.data.dto.ErrorDto
import com.google.gson.annotations.SerializedName

data class AllKkilogResult(
    @SerializedName("error")
    val error: ErrorDto?,
    @SerializedName("data")
    val data: List<AllKkilogItem>?,
)

data class AllKkilogItem(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: Kkilog,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
) {
    data class Kkilog(
        @SerializedName("original")
        val original: String,
        @SerializedName("w1024")
        val w1024: String,
        @SerializedName("w256")
        val w256: String,
    )
}
