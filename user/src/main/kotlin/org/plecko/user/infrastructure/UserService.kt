package org.plecko.user.infrastructure

import org.plecko.user.domain.FindUserUseCase
import org.plecko.user.domain.FindUsersUseCase
import org.plecko.user.domain.SaveUserUseCase
import org.plecko.user.domain.User
import org.springframework.stereotype.Service

@Service
class UserService(
        private val findUserUseCase: FindUserUseCase,
        private val saveUserUseCase: SaveUserUseCase,
        private val findUsersUseCase: FindUsersUseCase
) {
    fun findUser(id: String): User? = findUserUseCase.execute(id)
    fun saveUser(user: User) = saveUserUseCase.execute(user)
    fun findUsers() = findUsersUseCase.execute()
}