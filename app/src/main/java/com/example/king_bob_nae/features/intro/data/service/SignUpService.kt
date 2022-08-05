package com.example.king_bob_nae.features.intro.data.service

import com.example.king_bob_nae.features.intro.data.dto.AuthResponseDto
import com.example.king_bob_nae.features.intro.data.dto.CheckAuthDto
import com.example.king_bob_nae.features.intro.data.dto.CreateAuthDto
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
    suspend fun createCertification(
        @Body
        auth: CreateAuthDto,
    )

    // 인증번호 확인
    @POST("auth/code/check")
    suspend fun checkCertification(
        @Body
        authDto: CheckAuthDto
    )

    // 닉네임 중복 체크
    @GET("auth/validate/nickname")
    suspend fun validateNickname(
        @Query("nickname")
        nickName: String
    ): AuthResponseDto
}
