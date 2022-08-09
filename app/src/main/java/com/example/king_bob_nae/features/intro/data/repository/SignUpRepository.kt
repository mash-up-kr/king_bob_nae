package com.example.king_bob_nae.features.intro.data.repository

import com.example.king_bob_nae.features.intro.data.dto.TYPE

interface SignUpRepository {

    suspend fun checkEmail(email: String): Int

    suspend fun createCertification(email: String, type: TYPE): Int

    suspend fun checkCertification(email: String, type: TYPE, code: String): Int

    suspend fun validateNickname(nickname: String): Int
}
