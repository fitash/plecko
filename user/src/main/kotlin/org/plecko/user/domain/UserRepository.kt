package org.plecko.user.domain

interface UserRepository {
    fun find(id: String): User?
    fun save(user: User)
    fun findAll():Set<String>
}