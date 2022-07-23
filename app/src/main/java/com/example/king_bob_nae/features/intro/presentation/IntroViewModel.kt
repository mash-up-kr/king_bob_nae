package com.example.king_bob_nae.features.intro.presentation

import androidx.lifecycle.ViewModel

class IntroViewModel : ViewModel() {
    private val emailPattern = android.util.Patterns.EMAIL_ADDRESS

    fun isValidateEmail(email: String) =
        emailPattern.matcher(email).matches()

    fun isValidatePasswd(passwd: String) =
        passwd.length in 8..23

    fun isValidateNickname(nickname: String) =
        nickname.length in 2..10

    fun isSamePasswd(firstPasswd: String, secondPasswd: String) =
        firstPasswd == secondPasswd


    // 아직 미사용
    fun checkCertification() = true

    fun checkAuth(): Boolean = true

    fun checkNickname(nickname: String) = true
}