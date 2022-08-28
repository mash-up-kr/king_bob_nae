package com.example.king_bob_nae.features.myprofile.data.userfollow.getuserfollow

import com.example.king_bob_nae.features.myprofile.domain.userfollow.UsersFollowUiState

fun UserFollowResult.toUserFollowUiState() = this.data.map {
    UsersFollowUiState(id = it.id, name = it.name, imageUrl = it.imageUrl, following = it.following)
}
