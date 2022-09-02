package com.example.king_bob_nae.features.mykkilog.data.patchkkilog

import com.example.king_bob_nae.features.mykkilog.data.SimpleKkilogApi
import com.example.king_bob_nae.features.mykkilog.data.toPatchKkilogUiState
import com.example.king_bob_nae.features.mykkilog.domain.patchkkilog.PatchKkilogUiState
import com.example.king_bob_nae.features.mykkilog.domain.patchkkilog.RemotePatchKkilog
import javax.inject.Inject

class RemotePatchKkilogImpl @Inject constructor(private val simpleKkilogApi: SimpleKkilogApi) : RemotePatchKkilog {
    override suspend fun patchKkilog(id: Int): PatchKkilogUiState = simpleKkilogApi.patchKkilog(id).toPatchKkilogUiState()
}
