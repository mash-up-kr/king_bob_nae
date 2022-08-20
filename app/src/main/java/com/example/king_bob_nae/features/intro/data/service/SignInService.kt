package com.example.king_bob_nae.features.intro.data.service

import com.example.king_bob_nae.features.intro.data.dto.SignInDto
import com.example.king_bob_nae.features.intro.data.dto.SignInResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {

    @POST("auth/login")
    suspend fun signIn(
        @Body
        auth: SignInDto
    ): SignInResponseDto

}