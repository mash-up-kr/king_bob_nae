package com.example.king_bob_nae.features.signup.shared.data.service

import com.example.king_bob_nae.features.signup.shared.data.dto.AuthResponseDto
import com.example.king_bob_nae.features.signup.shared.data.dto.CheckAuthDto
import com.example.king_bob_nae.features.signup.shared.data.dto.CreateAuthDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

// FIXME api 테스트 후 수정 필요
interface SignUpService {
    // 이메일 중복 체크
    @GET("auth/validate/email")
    suspend fun checkEmail(
        @Query("email")
        email: String
    ): AuthResponseDto

    // 인증번호 생성 & 이메일 발송
    @POST("auth/code")
    suspend fun createAuthNumber(
        @Body
        auth: CreateAuthDto,
    ): AuthResponseDto

    // 인증번호 확인
    @POST("auth/code/check")
    suspend fun checkAuthCode(
        @Body
        authDto: CheckAuthDto
    ): AuthResponseDto

    // 닉네임 중복 체크
    @GET("auth/validate/nickname")
    suspend fun validateEmail(
        @Query("nickname")
        nickName: String
    ): AuthResponseDto
}
