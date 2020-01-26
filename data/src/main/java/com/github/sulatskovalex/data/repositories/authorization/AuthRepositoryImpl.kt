package com.github.sulatskovalex.data.repositories.authorization

import com.github.sulatskovalex.data.ChatAppServiceGrpc
import com.github.sulatskovalex.data.SignInBody
import com.github.sulatskovalex.data.SignUpBody
import com.github.sulatskovalex.data.common.call
import com.github.sulatskovalex.data.converters.DataToDomainConverter
import com.github.sulatskovalex.domain.ResultValue
import com.github.sulatskovalex.domain.authorization.AuthRepository
import com.github.sulatskovalex.domain.models.AuthResponse

class AuthRepositoryImpl(
    private val service: ChatAppServiceGrpc.ChatAppServiceStub,
    private val converter: DataToDomainConverter
) : AuthRepository {

    override suspend fun signIn(
        nickname: String,
        password: String
    ): ResultValue<AuthResponse> =
        call(
            service::signIn,
            SignInBody.newBuilder()
                .setNickname(nickname)
                .setPassword(password)
                .build(),
            converter::toDomainAuthResponse
        )

    override suspend fun signUp(
        name: String,
        nickname: String,
        password: String
    ): ResultValue<AuthResponse> =
        call(
            service::signUp,
            SignUpBody.newBuilder()
                .setName(name)
                .setNickname(nickname)
                .setPassword(password)
                .build(),
            converter::toDomainAuthResponse
        )
}