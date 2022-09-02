package com.example.king_bob_nae.features.mykkilog.domain.postkkilogunlike

import com.example.king_bob_nae.features.mykkilog.domain.postkkiloglike.LikeUiState

interface RemotePostKkilogUnLike {
    suspend fun postKkilogUnLike(id: Int): LikeUiState
}
