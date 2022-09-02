package com.example.king_bob_nae.features.mykkilog.domain.postkkilogscrap

import javax.inject.Inject

class PostKkilogScrapUseCase @Inject constructor(private val remotePostKkilogScrap: RemotePostKkilogScrap) {
    suspend operator fun invoke(id: Int) = remotePostKkilogScrap.postKkilogScrap(id)
}
