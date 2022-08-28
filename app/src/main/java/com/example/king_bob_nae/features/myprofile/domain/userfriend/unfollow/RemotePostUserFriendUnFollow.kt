package com.example.king_bob_nae.features.myprofile.domain.userfriend.unfollow

import com.example.king_bob_nae.features.myprofile.domain.userfriend.UserFriendUiState

interface RemotePostUserFriendUnFollow {
    suspend fun postUserFriendUnFollow(id: Int): UserFriendUiState
}
