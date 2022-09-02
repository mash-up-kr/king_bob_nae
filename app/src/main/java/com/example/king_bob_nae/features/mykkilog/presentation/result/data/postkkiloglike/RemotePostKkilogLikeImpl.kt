package com.example.king_bob_nae.features.mykkilog.presentation.result.data.postkkiloglike

import com.example.king_bob_nae.features.mykkilog.presentation.result.data.SimpleKkilogApi
import com.example.king_bob_nae.features.mykkilog.presentation.result.domain.postkkiloglike.LikeUiState
import com.example.king_bob_nae.features.mykkilog.presentation.result.domain.postkkiloglike.RemotePostKkilogLike
import javax.inject.Inject

class RemotePostKkilogLikeImpl @Inject constructor(private val simpleKkilogApi: SimpleKkilogApi) : RemotePostKkilogLike {
    override suspend fun postKkilogLike(id: Int): LikeUiState =
        if (simpleKkilogApi.postKkilogLike(id).isSuccessful) LikeUiState(true) else LikeUiState(false)
}
