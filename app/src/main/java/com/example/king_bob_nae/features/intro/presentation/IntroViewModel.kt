package com.example.king_bob_nae.features.intro.presentation

import androidx.lifecycle.ViewModel

class IntroViewModel : ViewModel() {
    private val emailPattern = android.util.Patterns.EMAIL_ADDRESS

    fun isValidateEmail(email: String): Boolean {
        return emailPattern.matcher(email).matches()
    }

    fun isValidatePasswd(passwd: String): Boolean {
        return passwd.length in 8..23
    }

    fun isValidateNickname(nickname: String) : Boolean{
        return nickname.length in 2..10
    }

    fun isSamePasswd(firstPasswd: String, secondPasswd: String): Boolean {
        return firstPasswd == secondPasswd
    }

    fun checkCertification(): Boolean {
        return true
    }

    fun checkAuth(): Boolean {
        return true
    }

    fun checkNickname(nickname: String): Boolean {
        return true
    }
}