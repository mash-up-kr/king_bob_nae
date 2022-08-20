package com.example.king_bob_nae.features.intro.data.repository.impl

import com.example.king_bob_nae.features.intro.data.dto.ResetPasswordDto
import com.example.king_bob_nae.features.intro.data.dto.SignInDto
import com.example.king_bob_nae.features.intro.data.dto.asAccessToken
import com.example.king_bob_nae.features.intro.data.repository.SignInRepository
import com.example.king_bob_nae.features.intro.data.service.SignInService
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val service: SignInService
) : SignInRepository {

    override suspend fun signIn(email: String, passwd: String): String? {
        val auth = SignInDto(email, passwd)
        return kotlin.runCatching {
            service.signIn(auth).asAccessToken().toString()
        }.getOrNull()
    }

    override suspend fun checkEmailExistence(email: String): Int =
        service.checkEmailExistence(email).code()

    override suspend fun resetPassword(
        email: String,
        newPassword: String,
        confirmPassword: String
    ): Int {
        val dto = ResetPasswordDto(email, newPassword, confirmPassword)
        return service.resetPassword(dto).code()
    }

}