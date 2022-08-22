package com.example.king_bob_nae.features.myprofile.domain

interface RemoteGetUserProfile {
    suspend fun remoteGetUserProfile(): UserProfileUiState
}
