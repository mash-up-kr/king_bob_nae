package com.example.king_bob_nae.features.intro.data.dto

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

// TODO CreateAuth, CheckAuth 합칠 수 있나 확인
@Keep
@Serializable
data class CreateAuthDto(
    val email: String,
    val type: TYPE
)

@Keep
@Serializable
data class CheckAuthDto(
    val email: String,
    val type: TYPE,
    val code: String?
)

@Keep
@Serializable
data class SignUpDto(
    val email: String = "",
    val nickname: String = "",
    val password: String = ""
)

@Keep
@Serializable
data class SignInDto(
    val email: String,
    val passwd: String
)

enum class TYPE {
    SIGN_UP, CHANGE_PASSWORD
}
