package com.example.king_bob_nae.features.home.domain.friendshome

import javax.inject.Inject

class GetAllKkilogUseCase @Inject constructor(private val getAllkkilog: RemoteGetAllkkilog) {
    suspend operator fun invoke(userId: Int) = getAllkkilog.getAllKkilog(userId)
}
