package com.example.king_bob_nae.features.intro.data.repository.impl

import com.example.king_bob_nae.features.intro.data.dto.AuthResponseDto
import com.example.king_bob_nae.features.intro.data.dto.CheckAuthDto
import com.example.king_bob_nae.features.intro.data.dto.CreateAuthDto
import com.example.king_bob_nae.features.intro.data.repository.SignUpRepository
import com.example.king_bob_nae.features.intro.data.service.SignUpService
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val service: SignUpService,
) : SignUpRepository {

    override suspend fun checkEmail(email: String): Int = service.checkEmail(email).code()

    override suspend fun createCertification(auth: CreateAuthDto): Int =
        service.createCertification(auth).code()

    override suspend fun checkCertification(auth: CheckAuthDto): AuthResponseDto =
        service.checkCertification(auth)

    override suspend fun validateNickname(nickname: String): AuthResponseDto =
        service.validateNickname(nickname)

}
