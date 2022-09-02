package com.example.king_bob_nae.features.mykkilog.domain.postkkilogunscrap

import com.example.king_bob_nae.features.mykkilog.domain.postkkilogscrap.ScrapUiState

interface RemotePostKkilogUnScrap {
    suspend fun postKkilogUnScrap(id: Int): ScrapUiState
}
