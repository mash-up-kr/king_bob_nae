package com.example.king_bob_nae.features.mykkilog.presentation.result.domain.deletekkilog

import javax.inject.Inject

class DeleteKkilogUseCase @Inject constructor(private val remoteDeleteKkilog: RemoteDeleteKkilog) {
    suspend operator fun invoke(id: Int) = remoteDeleteKkilog.deleteKkilog(id)
}
