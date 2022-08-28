package com.example.king_bob_nae.features.myprofile.data.userfollow.unfollow


import com.example.king_bob_nae.features.intro.data.dto.ErrorDto
import com.google.gson.annotations.SerializedName

data class UserFriendUnFollowResult(
    @SerializedName("error")
    val error: ErrorDto,
    @SerializedName("data")
    val data: UserFriendUnFollow,
)

data class UserFriendUnFollow(
    @SerializedName("following")
    val following: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
)
