package com.github.sulatskovalex.data.converters

import com.github.sulatskovalex.data.AuthResponse
import com.github.sulatskovalex.data.User
import com.github.sulatskovalex.data.UsersList

class DataToDomainConverter {
    fun toDomainUser(user: User): com.github.sulatskovalex.domain.models.User =
        com.github.sulatskovalex.domain.models.User(user.id, user.nickname, user.name)

    fun toDomainUsers(usersList: UsersList): List<com.github.sulatskovalex.domain.models.User> =
        usersList.usersList.mapTo(ArrayList(usersList.usersCount), { toDomainUser(it) })

    fun toDomainAuthResponse(response: AuthResponse): com.github.sulatskovalex.domain.models.AuthResponse =
        com.github.sulatskovalex.domain.models.AuthResponse(
            toDomainUser(response.user),
            response.accessToken,
            response.refreshToken
        )
}
