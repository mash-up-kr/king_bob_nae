package com.example.king_bob_nae.features.myprofile.domain.userprofile

interface RemoteGetUserProfile {
    suspend fun remoteGetUserProfile(): UserProfileUiState
}
