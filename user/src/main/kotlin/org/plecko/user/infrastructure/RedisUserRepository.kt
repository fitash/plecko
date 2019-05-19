package org.plecko.user.infrastructure

import io.lettuce.core.api.sync.RedisCommands
import org.plecko.user.domain.Source
import org.plecko.user.domain.User
import org.plecko.user.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class RedisUserRepository(@Autowired val redisConnection: RedisConnection) : UserRepository {
    private val prefix = "plecko.user"
    private val prefix_ids = "plecko.user.ids"
    override fun findAll(): Set<String> {
        val sync = redisConnection.sync()
        return sync.smembers(prefix_ids)
    }

    override fun find(id: String): User? {
        val sync = redisConnection.sync()
        return if (sync.exists("$prefix$id") > 0) {
            User(
                id,
                email = sync.hget("$prefix$id", "email"),
                name = sync.hget("$prefix$id", "name"),
                sources = parseSources(sync.hget("$prefix$id", "sources"))
            )
        } else null
    }

    override fun save(user: User) {
        val sync: RedisCommands<String, String> = redisConnection.sync()
        sync.multi()
        sync.hset("$prefix${user.id}", "id", user.email)
        sync.hset("$prefix${user.id}", "email", user.email)
        sync.hset("$prefix${user.id}", "name", user.name)
        sync.hset("$prefix${user.id}", "sources", serializeSources(user))
        sync.sadd(prefix_ids,user.id)
        sync.exec()
    }

    private fun serializeSources(user: User) = user.sources.map { it.id }.joinToString(",")

    private fun parseSources(sources: String?): List<Source> = sources?.split(",")?.map { Source(it) } ?: emptyList()
}


