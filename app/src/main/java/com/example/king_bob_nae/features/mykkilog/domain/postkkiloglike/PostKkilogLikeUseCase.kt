package com.example.king_bob_nae.features.mykkilog.domain.postkkiloglike

import javax.inject.Inject

class PostKkilogLikeUseCase @Inject constructor(private val remotePostKkilogLike: RemotePostKkilogLike) {
    suspend operator fun invoke(id: Int) = remotePostKkilogLike.postKkilogLike(id)
}
