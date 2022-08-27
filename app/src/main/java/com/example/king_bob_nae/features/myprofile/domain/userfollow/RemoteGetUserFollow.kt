package com.example.king_bob_nae.features.myprofile.domain.userfollow

interface RemoteGetUserFollow {
    suspend fun remoteGetUserFollow(type: String, keyword: String? = null): List<UsersFollowUiState>
}
