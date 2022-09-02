package com.example.king_bob_nae.features.mykkilog.presentation.result.domain.postkkilogunscrap

import javax.inject.Inject

class PostKkilogUnScrapUseCase @Inject constructor(private val remotePostKkilogUnScrap: RemotePostKkilogUnScrap) {
    suspend operator fun invoke(id: Int) = remotePostKkilogUnScrap.postKkilogUnScrap(id)
}
