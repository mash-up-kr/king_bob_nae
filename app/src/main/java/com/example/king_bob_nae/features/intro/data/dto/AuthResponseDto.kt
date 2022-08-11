package com.example.king_bob_nae.features.intro.data.dto

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

// FIXME data타입 별로 나누기
@Keep
@Serializable
data class AuthResponseDto(
    val data: Boolean?,
    val error: ErrorDto?
)

@Keep
@Serializable
data class CertificationResponseDto(
    val data: CertificationDto?,
    val error: ErrorDto?
)

@Keep
@Serializable
data class CertificationDto(
    val email: String,
    val expiredAt: String
)

@Keep
@Serializable
data class ErrorDto(
    val message: String,
    val description: String
)

@Keep
@Serializable
data class SignUpResponseDto(
    val data: SignUpDataDto,
    val error: ErrorDto?
)

@Keep
@Serializable
data class SignUpDataDto(
    val user: UserDto?,
    val character: CHARACTER,
    val token: String
)

@Keep
@Serializable
data class UserDto(
    val id: Int,
    val email: String,
    val createdAt: String,
    val updatedAt: String
)

enum class CHARACTER {
    BROCCOLI, CARROT, GREEN_ONION
}
