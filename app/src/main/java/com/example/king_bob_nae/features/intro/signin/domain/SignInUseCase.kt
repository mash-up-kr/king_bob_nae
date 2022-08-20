package com.example.king_bob_nae.features.intro.signin.domain

import com.example.king_bob_nae.features.intro.data.repository.SignInRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val signInRepository: SignInRepository
) {
    suspend operator fun invoke(email: String, passwd: String) =
        signInRepository.signIn(email, passwd)
}