package com.example.king_bob_nae.features.intro.signup.domain

import com.example.king_bob_nae.features.intro.data.repository.SignUpRepository
import javax.inject.Inject

class ValidateNicknameUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(nickname: String) = signUpRepository.validateNickname(nickname)
}