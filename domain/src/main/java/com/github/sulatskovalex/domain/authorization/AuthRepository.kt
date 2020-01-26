package com.github.sulatskovalex.domain.authorization

import com.github.sulatskovalex.domain.ResultValue
import com.github.sulatskovalex.domain.models.AuthResponse

interface AuthRepository {
    suspend fun signIn(nickname: String, password: String): ResultValue<AuthResponse>
    suspend fun signUp(name: String, nickname: String, password: String): ResultValue<AuthResponse>

}