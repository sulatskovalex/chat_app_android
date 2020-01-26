package com.github.sulatskovalex.domain.authorization

import com.github.sulatskovalex.domain.ResultValue
import com.github.sulatskovalex.domain.models.AuthResponse

class AuthInteractor(private val authRepository: AuthRepository) {
    suspend fun signIn(nickname: String, password: String): ResultValue<AuthResponse> =
        authRepository.signIn(nickname, password)

    suspend fun signUp(
        name: String,
        nickname: String,
        password: String
    ): ResultValue<AuthResponse> =
        authRepository.signUp(name, nickname, password)
}