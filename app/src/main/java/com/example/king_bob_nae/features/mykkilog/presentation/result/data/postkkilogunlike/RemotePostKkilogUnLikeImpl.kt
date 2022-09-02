package com.example.king_bob_nae.features.mykkilog.presentation.result.data.postkkilogunlike

import com.example.king_bob_nae.features.mykkilog.presentation.result.data.SimpleKkilogApi
import com.example.king_bob_nae.features.mykkilog.presentation.result.domain.postkkiloglike.LikeUiState
import com.example.king_bob_nae.features.mykkilog.presentation.result.domain.postkkilogunlike.RemotePostKkilogUnLike
import javax.inject.Inject

class RemotePostKkilogUnLikeImpl @Inject constructor(private val simpleKkilogApi: SimpleKkilogApi) : RemotePostKkilogUnLike {
    override suspend fun postKkilogUnLike(id: Int): LikeUiState =
        if (simpleKkilogApi.postKkilogUnLike(id).isSuccessful) LikeUiState(false) else LikeUiState(true)
}
