package com.example.king_bob_nae.features.mykkilog.presentation.result.data.postkkilogscrap

import com.example.king_bob_nae.features.mykkilog.presentation.result.data.SimpleKkilogApi
import com.example.king_bob_nae.features.mykkilog.presentation.result.domain.postkkilogscrap.RemotePostKkilogScrap
import com.example.king_bob_nae.features.mykkilog.presentation.result.domain.postkkilogscrap.ScrapUiState
import javax.inject.Inject

class RemotePostKkilogScrapImpl @Inject constructor(private val simpleKkilogApi: SimpleKkilogApi) : RemotePostKkilogScrap {
    override suspend fun postKkilogScrap(id: Int): ScrapUiState =
        if (simpleKkilogApi.postKkilogScrap(id).isSuccessful) ScrapUiState(true) else ScrapUiState(false)
}
