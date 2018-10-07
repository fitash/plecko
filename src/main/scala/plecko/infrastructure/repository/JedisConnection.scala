package plecko.infrastructure.repository

import redis.clients.jedis.{Jedis, JedisPool}

object JedisConnection {

  def withJedis(f: Jedis => Unit)(implicit pool: JedisPool): Unit = {
    val jedis = pool.getResource
    try {
      f(jedis)
    }
    finally {
      jedis.close()
    }
  }

}
