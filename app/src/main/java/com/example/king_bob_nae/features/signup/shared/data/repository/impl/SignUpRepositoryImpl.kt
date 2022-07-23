package com.example.king_bob_nae.features.signup.shared.data.repository.impl

import com.example.king_bob_nae.features.signup.shared.data.repository.SignUpRepository
import com.example.king_bob_nae.features.signup.shared.data.service.SignUpService
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val service: SignUpService
) : SignUpRepository
