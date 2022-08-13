package com.example.king_bob_nae.features.intro.signup.domain

import com.example.king_bob_nae.features.intro.data.dto.TYPE
import com.example.king_bob_nae.features.intro.data.repository.SignUpRepository
import javax.inject.Inject

class CreateCertificationUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(email: String, type: TYPE) =
        signUpRepository.createCertification(email, type)
}