package com.example.king_bob_nae.features.mykkilog.data.postkkilogunscrap

import com.example.king_bob_nae.features.mykkilog.data.SimpleKkilogApi
import com.example.king_bob_nae.features.mykkilog.domain.postkkilogscrap.ScrapUiState
import com.example.king_bob_nae.features.mykkilog.domain.postkkilogunscrap.RemotePostKkilogUnScrap
import javax.inject.Inject

class RemotePostKkilogUnScrapImpl @Inject constructor(private val simpleKkilogApi: SimpleKkilogApi) : RemotePostKkilogUnScrap {
    override suspend fun postKkilogUnScrap(id: Int): ScrapUiState =
        if (simpleKkilogApi.postKkilogUnScrap(id).isSuccessful) ScrapUiState(false) else ScrapUiState(true)
}
