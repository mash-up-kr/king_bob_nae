package com.example.king_bob_nae.features.intro.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.king_bob_nae.KkiLogApplication
import com.example.king_bob_nae.features.intro.data.dto.CHARACTER
import com.example.king_bob_nae.features.intro.data.dto.SignUpDto
import com.example.king_bob_nae.features.intro.data.dto.TYPE
import com.example.king_bob_nae.features.intro.data.dto.asCharacter
import com.example.king_bob_nae.features.intro.signin.domain.SignInUseCase
import com.example.king_bob_nae.features.intro.signin.domain.model.SignInResponse
import com.example.king_bob_nae.features.intro.signup.domain.*
import com.example.king_bob_nae.features.intro.signup.domain.model.AuthResponse
import com.example.king_bob_nae.utils.Extensions.Companion.CERTIFICATION_ERROR
import com.example.king_bob_nae.utils.Extensions.Companion.EMAIL_FORMAT_ERROR
import com.example.king_bob_nae.utils.Extensions.Companion.EMAIL_USE_ERROR
import com.example.king_bob_nae.utils.Extensions.Companion.NICK_ERROR
import com.example.king_bob_nae.utils.Extensions.Companion.NICK_SIZE_ERROR
import com.example.king_bob_nae.utils.Extensions.Companion.SERVER_ERROR
import com.example.king_bob_nae.utils.Extensions.Companion.SIGN_IN_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val checkEmailDuplicatedUseCase: CheckEmailDuplicatedUseCase,
    private val checkCertificationUseCase: CheckCertificationUseCase,
    private val createCertificationUseCase: CreateCertificationUseCase,
    private val validateNicknameUseCase: ValidateNicknameUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _result = MutableSharedFlow<AuthResponse>()
    val result = _result.asSharedFlow()

    private val _character = MutableSharedFlow<CHARACTER>()
    val character = _character.asSharedFlow()

    private val _signInResult = MutableSharedFlow<SignInResponse>()
    val signInResult = _signInResult.asSharedFlow()

    private val auth = MutableStateFlow(SignUpDto())

    // 이메일 중복검사
    fun checkEmailDuplicated(email: String) {
        viewModelScope.launch {
            _result.emit(
                when (checkEmailDuplicatedUseCase(email)) {
                    200 -> AuthResponse(200, isDuplicatedEmail = true)
                    400 -> AuthResponse(EMAIL_FORMAT_ERROR)
                    409 -> AuthResponse(EMAIL_USE_ERROR)
                    else -> AuthResponse(SERVER_ERROR)
                }
            )
        }
    }

    // 인증번호 생성
    fun createCertification(email: String, type: TYPE) {
        viewModelScope.launch {
            _result.emit(
                when (createCertificationUseCase(email, type)) {
                    201 -> AuthResponse(201, createCertification = true)
                    else -> AuthResponse(SERVER_ERROR)
                }
            )
        }
    }

    // 인증번호 검사
    fun checkCertification(email: String, type: TYPE, code: String) {
        viewModelScope.launch {
            _result.emit(
                when (checkCertificationUseCase(email, type, code)) {
                    201 -> AuthResponse(201, checkCertification = true)
                    else -> AuthResponse(CERTIFICATION_ERROR)
                }
            )
        }
    }

    // 닉네임 검사
    fun checkNickname(nickname: String) {
        viewModelScope.launch {
            _result.emit(
                when (validateNicknameUseCase(nickname)) {
                    200 -> AuthResponse(200, checkNickname = true)
                    400 -> AuthResponse(NICK_SIZE_ERROR)
                    409 -> AuthResponse(NICK_ERROR)
                    else -> AuthResponse(SERVER_ERROR)
                }
            )
        }
    }

    // 회원가입
    fun signUp() {
        viewModelScope.launch {
            signUpUseCase(auth.value).asCharacter()?.let { character ->
                _character.emit(character)
            }
        }
    }

    // 로그인
    fun signIn(email: String, passwd: String) {
        viewModelScope.launch {
            val token = signInUseCase(email, passwd)
            _signInResult.emit(
                when (token) {
                    null -> {
                        SignInResponse(SIGN_IN_ERROR, false)
                    }
                    else -> {
                        KkiLogApplication.prefs.accessToken = token
                        SignInResponse(201, true)
                    }
                }
            )
        }
    }

    fun setAuthEmail(email: String) {
        auth.update { auth.value.copy(email = email) }
    }

    fun setAuthPasswd(passwd: String) {
        auth.update { auth.value.copy(password = passwd) }
    }

    fun setAuthNick(nickname: String) {
        auth.update { auth.value.copy(nickname = nickname) }
    }

}