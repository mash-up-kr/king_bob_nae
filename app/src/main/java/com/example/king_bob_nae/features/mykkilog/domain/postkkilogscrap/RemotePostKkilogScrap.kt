package com.example.king_bob_nae.features.mykkilog.domain.postkkilogscrap

interface RemotePostKkilogScrap {
    suspend fun postKkilogScrap(id: Int): ScrapUiState
}
