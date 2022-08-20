package com.example.king_bob_nae.features.home.data.freindlist


import com.example.king_bob_nae.features.intro.data.dto.ErrorDto
import com.google.gson.annotations.SerializedName

data class Friends(
    @SerializedName("error")
    val error: ErrorDto,
    @SerializedName("data")
    val friends: List<Friend>
) {
    data class Friend(
        @SerializedName("id")
        val id: Int,
        @SerializedName("imageUrl")
        val imageUrl: String,
        @SerializedName("name")
        val name: String
    )
}
