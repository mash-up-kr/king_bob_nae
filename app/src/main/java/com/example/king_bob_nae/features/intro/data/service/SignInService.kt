package com.example.king_bob_nae.features.intro.data.service

import com.example.king_bob_nae.features.intro.data.dto.AuthResponseDto
import com.example.king_bob_nae.features.intro.data.dto.ResetPasswordDto
import com.example.king_bob_nae.features.intro.data.dto.SignInDto
import com.example.king_bob_nae.features.intro.data.dto.SignInResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SignInService {

    @POST("auth/login")
    suspend fun signIn(
        @Body
        auth: SignInDto
    ): SignInResponseDto

    @GET("auth/validate/email/exist")
    suspend fun checkEmailExistence(
        @Query("email")
        email: String
    ): Response<AuthResponseDto>

    @POST("auth/password/reset")
    suspend fun resetPassword(
        @Body
        resetDto: ResetPasswordDto
    ): Response<AuthResponseDto>

}