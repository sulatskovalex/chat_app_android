package com.github.sulatskovalex.domain.users

import com.github.sulatskovalex.domain.ResultValue
import com.github.sulatskovalex.domain.models.User

class UsersInteractor(private val usersRepository: UsersRepository) {
    suspend fun getUser(userId: Long): ResultValue<User> = usersRepository.getUser(userId)
    suspend fun getUsers(userIds: List<Long>): ResultValue<List<User>> = usersRepository.getUsers(userIds)
}