package com.example.king_bob_nae.features.home.domain.friendshome

interface RemoteGetAllkkilog {
    suspend fun getAllKkilog(userId: Int): List<KkilogState>?
}
