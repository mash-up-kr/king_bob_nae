package com.example.king_bob_nae.features.intro.signup.domain

import com.example.king_bob_nae.features.intro.data.dto.CheckAuthDto
import com.example.king_bob_nae.features.intro.data.repository.SignUpRepository
import javax.inject.Inject

class CheckCertificationUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(auth: CheckAuthDto) = signUpRepository.checkCertification(auth)
}