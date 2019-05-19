package org.plecko.user.domain

class SaveUserUseCase(private val repository: UserRepository) {
    fun execute(user: User): Unit =repository.save(user)
}