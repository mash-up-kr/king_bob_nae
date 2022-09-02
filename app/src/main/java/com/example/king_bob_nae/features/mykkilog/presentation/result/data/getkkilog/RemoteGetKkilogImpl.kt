package com.example.king_bob_nae.features.mykkilog.presentation.result.data.getkkilog

import com.example.king_bob_nae.features.mykkilog.presentation.result.data.SimpleKkilogApi
import com.example.king_bob_nae.features.mykkilog.presentation.result.data.toKkilogResultUiState
import com.example.king_bob_nae.features.mykkilog.presentation.result.domain.getkkilog.KkilogResultUiState
import com.example.king_bob_nae.features.mykkilog.presentation.result.domain.getkkilog.RemoteGetKkilog
import javax.inject.Inject

class RemoteGetKkilogImpl @Inject constructor(private val simpleKkilogApi: SimpleKkilogApi) : RemoteGetKkilog {
    override suspend fun getKkilog(id: Int): KkilogResultUiState = simpleKkilogApi.getKkilog(id).toKkilogResultUiState()
}
