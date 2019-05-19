package org.plecko.user.infrastructure

import io.lettuce.core.RedisClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile


@Configuration
open class RedisConfig {

    @Bean
    open fun redisClient(@Value("\${redis.port}") port: Int) = RedisClient.create("redis://localhost:$port/")

    @Profile("test")
    @Bean
    open fun redisConnectionTest(redisClient: RedisClient, @Value("\${redis.port}") port: Int): RedisConnection {
        println("--------------------------------------------creating test at $port ")
       return RedisConnectionTest(redisClient, port)
    }

    @Profile("!test")
    @Bean
    open fun redisConnectionProduction(redisClient: RedisClient, @Value("\${redis.port}") port: Int): RedisConnection {
        println("--------------------------------------------creating prod at $port ")
        return RedisConnectionProduction(redisClient, port)
    }

}