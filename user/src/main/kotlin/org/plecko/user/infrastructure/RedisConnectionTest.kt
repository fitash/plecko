package org.plecko.user.infrastructure

import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands
import redis.embedded.RedisServer
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

class RedisConnectionTest(val redisClient: RedisClient, port: Int) : RedisConnection {
    private lateinit var connection: StatefulRedisConnection<String, String>
    private val server = RedisServer(port)

    @PostConstruct
    fun init() {
        server.start()
        connection = redisClient.connect()
    }

    @PreDestroy
    fun stop() {
        server.stop()
        connection.close()
    }

    override fun sync():RedisCommands<String,String> = connection.sync()

}