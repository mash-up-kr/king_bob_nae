package com.example.king_bob_nae.features.myprofile.domain.serach

import com.example.king_bob_nae.features.myprofile.domain.userfollow.UsersFollowUiState

interface RemoteSearchFriend {
    suspend fun remoteSearchFriend(type: String, keyword: String? = null): List<UsersFollowUiState>
}