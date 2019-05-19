package org.plecko.user.infrastructure

import io.lettuce.core.api.sync.RedisCommands

interface RedisConnection {
    fun sync(): RedisCommands<String, String>
}