package com.example.king_bob_nae.features.intro.data.repository.impl

import com.example.king_bob_nae.features.intro.data.dto.AuthResponseDto
import com.example.king_bob_nae.features.intro.data.dto.SignInDto
import com.example.king_bob_nae.features.intro.data.repository.SignInRepository
import com.example.king_bob_nae.features.intro.data.service.SignInService
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val service: SignInService
) : SignInRepository {

    override suspend fun signIn(auth: SignInDto): AuthResponseDto = service.signIn(auth)

}