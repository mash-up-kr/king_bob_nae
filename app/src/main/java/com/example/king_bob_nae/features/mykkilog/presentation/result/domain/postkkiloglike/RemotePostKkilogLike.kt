package com.example.king_bob_nae.features.mykkilog.presentation.result.domain.postkkiloglike

interface RemotePostKkilogLike {
    suspend fun postKkilogLike(id: Int): LikeUiState
}
