package com.example.king_bob_nae.features.mykkilog.domain.deletekkilog

interface RemoteDeleteKkilog {
    suspend fun deleteKkilog(id: Int): KkilogDelteResultState
}
