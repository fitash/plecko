package org.plecko.user.domain

class FindUserUseCase(private val repository: UserRepository) {
    fun execute(id: String): User? =repository.find(id)
}