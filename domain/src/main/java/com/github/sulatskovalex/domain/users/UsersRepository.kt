package com.github.sulatskovalex.domain.users

import com.github.sulatskovalex.domain.ResultValue
import com.github.sulatskovalex.domain.models.User

interface UsersRepository {
    suspend fun getUser(userId: Long): ResultValue<User>
    suspend fun getUsers(userIds: List<Long>): ResultValue<List<User>>
}
