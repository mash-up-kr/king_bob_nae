package com.example.king_bob_nae.features.home.domain.userstate

interface RemoteGetHomeUserStatus {
    suspend fun getUserInformation(): HomeUserState
}
