package org.plecko.user.domain

class FindUsersUseCase(private val repository: UserRepository) {
    fun execute(): Set<User> =repository.findAll().mapNotNull{ repository.find(it) }.toSet()
}