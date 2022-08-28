package com.example.king_bob_nae.features.myprofile.domain.userfriend.follow

import com.example.king_bob_nae.features.myprofile.domain.userfriend.UserFriendUiState

interface RemotePostUserFriendFollow {
    suspend fun postUserFriendFollow(id: Int): UserFriendUiState
}
