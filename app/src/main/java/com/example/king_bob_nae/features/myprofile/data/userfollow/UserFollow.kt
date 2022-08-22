package com.example.king_bob_nae.features.myprofile.data.userfollow

import com.example.king_bob_nae.features.intro.data.dto.ErrorDto
import com.example.king_bob_nae.features.myprofile.domain.userfollow.UsersFollowUiState
import com.google.gson.annotations.SerializedName

data class UserFollowResult(
    val error: ErrorDto,
    val data: List<UsersFollowUiState>,
)

data class UserFollow(
    @SerializedName("following")
    val following: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
)
