package com.github.sulatskovalex.data.repositories.users

import com.github.sulatskovalex.data.AccessTokenProvider
import com.github.sulatskovalex.data.ChatAppServiceGrpc
import com.github.sulatskovalex.data.GetUserBody
import com.github.sulatskovalex.data.GetUsersBody
import com.github.sulatskovalex.data.common.call
import com.github.sulatskovalex.data.converters.DataToDomainConverter
import com.github.sulatskovalex.domain.ResultValue
import com.github.sulatskovalex.domain.models.User
import com.github.sulatskovalex.domain.users.UsersRepository

class UsersRepositoryImpl(
    private val service: ChatAppServiceGrpc.ChatAppServiceStub,
    private val converter: DataToDomainConverter,
    private val accessTokenProvider: AccessTokenProvider
) : UsersRepository {
    override suspend fun getUser(userId: Long): ResultValue<User> =
        call(
            service::getUser,
            GetUserBody.newBuilder()
                .setAccessToken(accessTokenProvider.accessToken)
                .setUserId(userId)
                .build(),
            converter::toDomainUser
        )

    override suspend fun getUsers(userIds: List<Long>): ResultValue<List<User>> =
        call(
            service::getUsers,
            GetUsersBody.newBuilder()
                .setAccessToken(accessTokenProvider.accessToken)
                .addAllUserIds(userIds)
                .build(),
            converter::toDomainUsers
        )

}