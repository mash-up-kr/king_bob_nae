package com.example.king_bob_nae.features.intro.data.repository

import com.example.king_bob_nae.features.intro.data.dto.AuthResponseDto
import com.example.king_bob_nae.features.intro.data.dto.SignInDto

interface SignInRepository {

    suspend fun signIn(auth: SignInDto): AuthResponseDto

}