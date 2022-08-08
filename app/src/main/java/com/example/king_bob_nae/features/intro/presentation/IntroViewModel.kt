package com.example.king_bob_nae.features.intro.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.king_bob_nae.features.intro.data.mapper.AuthResponse
import com.example.king_bob_nae.features.intro.signin.domain.SignInUseCase
import com.example.king_bob_nae.features.intro.signup.domain.CheckCertificationUseCase
import com.example.king_bob_nae.features.intro.signup.domain.CheckEmailUseCase
import com.example.king_bob_nae.features.intro.signup.domain.CreateCertificationUseCase
import com.example.king_bob_nae.features.intro.signup.domain.ValidateNicknameUseCase
import com.example.king_bob_nae.utils.Extensions.Companion.EMAIL_FORMAT_ERROR
import com.example.king_bob_nae.utils.Extensions.Companion.EMAIL_USE_ERROR
import com.example.king_bob_nae.utils.NLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val checkEmailUseCase: CheckEmailUseCase,
    private val checkCertificationUseCase: CheckCertificationUseCase,
    private val createCertificationUseCase: CreateCertificationUseCase,
    private val validateNicknameUseCase: ValidateNicknameUseCase,
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _result = MutableStateFlow(AuthResponse(null, 100))
    val result = _result.asStateFlow()

    // 이메일 중복검사
    fun checkEmailDuplicated(email: String) {
        viewModelScope.launch {
            _result.value = when (checkEmailUseCase(email)) {
                200 -> {
                    AuthResponse(true, 200)
                }
                400 -> {
                    AuthResponse(false, EMAIL_FORMAT_ERROR)
                }
                else -> {
                    AuthResponse(false, EMAIL_USE_ERROR)
                }
            }
            NLog.d("checkEmailDuplicated", result.value.code.toString())
        }
    }

    // 아직 미사용
    fun checkCertification() = true

    fun checkAuth(): Boolean = true

    fun checkNickname(nickname: String) = true
}