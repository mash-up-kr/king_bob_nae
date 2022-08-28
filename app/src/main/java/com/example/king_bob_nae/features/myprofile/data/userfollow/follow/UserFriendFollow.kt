package com.example.king_bob_nae.features.myprofile.data.userfollow.follow


import com.example.king_bob_nae.features.intro.data.dto.ErrorDto
import com.google.gson.annotations.SerializedName

data class UserFriendFollowResult(
    val error: ErrorDto,
    val data: UserFriendFollow,
)

data class UserFriendFollow(
    @SerializedName("following")
    val following: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
)
