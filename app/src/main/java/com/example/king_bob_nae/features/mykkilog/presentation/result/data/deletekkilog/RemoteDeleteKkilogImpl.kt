package com.example.king_bob_nae.features.mykkilog.presentation.result.data.deletekkilog

import com.example.king_bob_nae.features.mykkilog.presentation.result.data.SimpleKkilogApi
import com.example.king_bob_nae.features.mykkilog.presentation.result.domain.deletekkilog.KkilogDelteResultState
import com.example.king_bob_nae.features.mykkilog.presentation.result.domain.deletekkilog.RemoteDeleteKkilog
import javax.inject.Inject

class RemoteDeleteKkilogImpl @Inject constructor(private val simpleKkilogApi: SimpleKkilogApi) : RemoteDeleteKkilog {
    override suspend fun deleteKkilog(id: Int): KkilogDelteResultState =
        if (simpleKkilogApi.deleteKkilog(id).isSuccessful) KkilogDelteResultState(true) else KkilogDelteResultState(false)
}
