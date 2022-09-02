package com.example.king_bob_nae.features.mykkilog.presentation.result.domain.postkkilogunlike

import com.example.king_bob_nae.features.mykkilog.presentation.result.domain.postkkiloglike.LikeUiState

interface RemotePostKkilogUnLike {
    suspend fun postKkilogUnLike(id: Int): LikeUiState
}
