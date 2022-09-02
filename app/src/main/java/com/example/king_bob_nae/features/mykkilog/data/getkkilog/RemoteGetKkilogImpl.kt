package com.example.king_bob_nae.features.mykkilog.data.getkkilog

import com.example.king_bob_nae.features.mykkilog.data.SimpleKkilogApi
import com.example.king_bob_nae.features.mykkilog.data.toKkilogResultUiState
import com.example.king_bob_nae.features.mykkilog.domain.getkkilog.KkilogResultUiState
import com.example.king_bob_nae.features.mykkilog.domain.getkkilog.RemoteGetKkilog
import javax.inject.Inject

class RemoteGetKkilogImpl @Inject constructor(private val simpleKkilogApi: SimpleKkilogApi) : RemoteGetKkilog {
    override suspend fun getKkilog(id: Int): KkilogResultUiState = simpleKkilogApi.getKkilog(id).toKkilogResultUiState()
}
