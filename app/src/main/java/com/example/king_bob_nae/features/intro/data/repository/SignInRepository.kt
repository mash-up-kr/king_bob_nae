package com.example.king_bob_nae.features.intro.data.repository

interface SignInRepository {

    suspend fun signIn(email: String, passwd: String): String?

    suspend fun checkEmailExistence(email: String): Int

    suspend fun resetPassword(email: String, newPassword: String, confirmPassword: String): Int

}