package com.example.king_bob_nae.features.mykkilog.presentation.result.domain.postkkilogscrap

interface RemotePostKkilogScrap {
    suspend fun postKkilogScrap(id: Int): ScrapUiState
}
