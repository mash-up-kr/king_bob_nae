package com.example.king_bob_nae.features.mykkilog.domain.postkkilogunlike

import javax.inject.Inject

class PostKkilogUnLikeUseCase @Inject constructor(private val remotePostKkilogUnLike: RemotePostKkilogUnLike) {
    suspend operator fun invoke(id: Int) = remotePostKkilogUnLike.postKkilogUnLike(id)
}
