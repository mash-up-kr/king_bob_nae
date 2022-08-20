package com.example.king_bob_nae.features.intro.reset.domain

import com.example.king_bob_nae.features.intro.data.repository.SignInRepository
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(
    private val signInRepository: SignInRepository
) {
    suspend operator fun invoke(email: String, newPassword: String, confirmPassword: String) =
        signInRepository.resetPassword(email, newPassword, confirmPassword)
}