package com.example.king_bob_nae.features.mykkilog.presentation.result.domain.patchkkilog

interface RemotePatchKkilog {
    suspend fun patchKkilog(id: Int): PatchKkilogUiState
}
