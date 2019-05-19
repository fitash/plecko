package org.plecko.user.infrastructure

import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

class RedisConnectionProduction(val redisClient: RedisClient, port: Int) : RedisConnection {
    private lateinit var connection: StatefulRedisConnection<String, String>

    @PostConstruct
    fun init() {
        connection = redisClient.connect()
    }

    @PreDestroy
    fun stop() {
        connection.close()
    }

    override fun sync(): RedisCommands<String, String> = connection.sync()
}
