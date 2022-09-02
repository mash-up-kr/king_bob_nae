package com.example.king_bob_nae.features.mykkilog.data.deletekkilog

import com.example.king_bob_nae.features.mykkilog.data.SimpleKkilogApi
import com.example.king_bob_nae.features.mykkilog.domain.deletekkilog.KkilogDelteResultState
import com.example.king_bob_nae.features.mykkilog.domain.deletekkilog.RemoteDeleteKkilog
import javax.inject.Inject

class RemoteDeleteKkilogImpl @Inject constructor(private val simpleKkilogApi: SimpleKkilogApi) : RemoteDeleteKkilog {
    override suspend fun deleteKkilog(id: Int): KkilogDelteResultState =
        if (simpleKkilogApi.deleteKkilog(id).isSuccessful) KkilogDelteResultState(true) else KkilogDelteResultState(false)
}
