package com.example.king_bob_nae.features.mykkilog.domain.patchkkilog

interface RemotePatchKkilog {
    suspend fun patchKkilog(id: Int): PatchKkilogUiState
}
