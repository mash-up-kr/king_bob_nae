package com.example.king_bob_nae.features.intro.data.repository

import com.example.king_bob_nae.features.intro.data.dto.AuthResponseDto
import com.example.king_bob_nae.features.intro.data.dto.CheckAuthDto
import com.example.king_bob_nae.features.intro.data.dto.CreateAuthDto

interface SignUpRepository {

    suspend fun checkEmail(email: String): Int

    suspend fun createCertification(auth: CreateAuthDto): Int

    suspend fun checkCertification(auth: CheckAuthDto): AuthResponseDto

    suspend fun validateNickname(nickname: String): AuthResponseDto
}
