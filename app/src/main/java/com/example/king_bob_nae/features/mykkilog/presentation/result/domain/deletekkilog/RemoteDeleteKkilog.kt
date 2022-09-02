package com.example.king_bob_nae.features.mykkilog.presentation.result.domain.deletekkilog

interface RemoteDeleteKkilog {
    suspend fun deleteKkilog(id: Int): KkilogDelteResultState
}
