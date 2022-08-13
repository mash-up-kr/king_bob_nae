package com.example.king_bob_nae.features.intro.signup.domain

import com.example.king_bob_nae.features.intro.data.dto.SignUpDto
import com.example.king_bob_nae.features.intro.data.repository.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(auth: SignUpDto) = signUpRepository.signUp(auth)
}