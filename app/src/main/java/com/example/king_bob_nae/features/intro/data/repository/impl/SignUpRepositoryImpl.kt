package com.example.king_bob_nae.features.intro.data.repository.impl

import com.example.king_bob_nae.features.intro.data.dto.CheckAuthDto
import com.example.king_bob_nae.features.intro.data.dto.CreateAuthDto
import com.example.king_bob_nae.features.intro.data.dto.TYPE
import com.example.king_bob_nae.features.intro.data.repository.SignUpRepository
import com.example.king_bob_nae.features.intro.data.service.SignUpService
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val service: SignUpService,
) : SignUpRepository {

    override suspend fun checkEmail(email: String): Int = service.checkEmail(email).code()

    override suspend fun createCertification(email: String, type: TYPE): Int {
        val auth = CreateAuthDto(email, type)
        return service.createCertification(auth).code()
    }

    override suspend fun checkCertification(email: String, type: TYPE, code: String): Int {
        val auth = CheckAuthDto(email, type, code)
        return service.checkCertification(auth).code()
    }

    override suspend fun validateNickname(nickname: String): Int =
        service.validateNickname(nickname).code()

}
