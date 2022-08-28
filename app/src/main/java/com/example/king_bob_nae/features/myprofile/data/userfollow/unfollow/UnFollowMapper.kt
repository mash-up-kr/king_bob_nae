package com.example.king_bob_nae.features.myprofile.data.userfollow.unfollow

import com.example.king_bob_nae.features.myprofile.domain.userfriend.UserFriendUiState

fun UserFriendUnFollow.toUserFriendUiState() = UserFriendUiState(following = this.following, id = this.id, imageUrl = this.imageUrl, name = this.name)
