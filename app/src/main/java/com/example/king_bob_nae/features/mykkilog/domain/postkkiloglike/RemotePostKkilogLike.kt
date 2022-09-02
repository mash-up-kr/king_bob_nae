package com.example.king_bob_nae.features.mykkilog.domain.postkkiloglike

interface RemotePostKkilogLike {
    suspend fun postKkilogLike(id: Int): LikeUiState
}
