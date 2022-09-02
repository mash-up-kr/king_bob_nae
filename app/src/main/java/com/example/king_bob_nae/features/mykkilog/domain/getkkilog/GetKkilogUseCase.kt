package com.example.king_bob_nae.features.mykkilog.domain.getkkilog

import javax.inject.Inject

class GetKkilogUseCase @Inject constructor(private val remoteGetKkilog: RemoteGetKkilog) {
    suspend operator fun invoke(id: Int) = remoteGetKkilog.getKkilog(id)
}
