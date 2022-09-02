package com.example.king_bob_nae.features.mykkilog.presentation.result.domain.postkkilogunscrap

import com.example.king_bob_nae.features.mykkilog.presentation.result.domain.postkkilogscrap.ScrapUiState

interface RemotePostKkilogUnScrap {
    suspend fun postKkilogUnScrap(id: Int): ScrapUiState
}
