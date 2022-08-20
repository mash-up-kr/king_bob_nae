package com.example.king_bob_nae.features.intro.signup.domain.model

data class AuthResponse(
    val code: Int,
    val isDuplicatedEmail: Boolean = false,
    val createCertification: Boolean = false,
    val checkCertification: Boolean = false,
    val checkNickname: Boolean = false,
    val checkEmailExistence: Boolean = false,
    val resetPassword: Boolean = false
)