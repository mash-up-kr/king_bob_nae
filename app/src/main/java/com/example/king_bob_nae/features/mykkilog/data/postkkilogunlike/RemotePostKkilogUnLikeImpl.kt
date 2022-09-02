package com.example.king_bob_nae.features.mykkilog.data.postkkilogunlike

import com.example.king_bob_nae.features.mykkilog.data.SimpleKkilogApi
import com.example.king_bob_nae.features.mykkilog.domain.postkkiloglike.LikeUiState
import com.example.king_bob_nae.features.mykkilog.domain.postkkilogunlike.RemotePostKkilogUnLike
import javax.inject.Inject

class RemotePostKkilogUnLikeImpl @Inject constructor(private val simpleKkilogApi: SimpleKkilogApi) : RemotePostKkilogUnLike {
    override suspend fun postKkilogUnLike(id: Int): LikeUiState =
        if (simpleKkilogApi.postKkilogUnLike(id).isSuccessful) LikeUiState(false) else LikeUiState(true)
}
