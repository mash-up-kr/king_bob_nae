package com.example.king_bob_nae.features.mykkilog.domain.patchkkilog

import javax.inject.Inject

class PatchKkilogUseCase @Inject constructor(private val remotePatchKkilog: RemotePatchKkilog) {
    suspend operator fun invoke(id: Int) = remotePatchKkilog.patchKkilog(id)
}
