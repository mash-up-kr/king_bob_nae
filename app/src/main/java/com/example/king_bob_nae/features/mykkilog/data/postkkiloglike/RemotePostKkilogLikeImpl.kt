package com.example.king_bob_nae.features.mykkilog.data.postkkiloglike

import com.example.king_bob_nae.features.mykkilog.data.SimpleKkilogApi
import com.example.king_bob_nae.features.mykkilog.domain.postkkiloglike.LikeUiState
import com.example.king_bob_nae.features.mykkilog.domain.postkkiloglike.RemotePostKkilogLike
import javax.inject.Inject

class RemotePostKkilogLikeImpl @Inject constructor(private val simpleKkilogApi: SimpleKkilogApi) : RemotePostKkilogLike {
    override suspend fun postKkilogLike(id: Int): LikeUiState =
        if (simpleKkilogApi.postKkilogLike(id).isSuccessful) LikeUiState(true) else LikeUiState(false)
}
