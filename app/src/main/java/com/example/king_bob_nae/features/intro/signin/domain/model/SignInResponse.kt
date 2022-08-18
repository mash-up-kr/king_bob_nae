package com.example.king_bob_nae.features.intro.signin.domain.model

import com.example.king_bob_nae.utils.Extensions.Companion.SIGN_IN_ERROR

data class SignInResponse(
    val code: Int = SIGN_IN_ERROR,
    val result: Boolean = false
)
