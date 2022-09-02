package com.example.king_bob_nae.features.mykkilog.domain.getkkilog

interface RemoteGetKkilog {
    suspend fun getKkilog(id: Int): KkilogResultUiState
}
