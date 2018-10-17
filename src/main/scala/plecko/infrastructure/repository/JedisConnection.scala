package plecko.infrastructure.repository

import redis.clients.jedis.{Jedis, JedisPool}

case class JedisConnection(val pool: JedisPool, val database: Int)

object JedisConnection {

  def withJedis(f: Jedis => Unit)(implicit connection: JedisConnection): Unit = {
    val jedis = connection.pool.getResource
    jedis.select(connection.database)
    try {
      f(jedis)
    }
    finally {
      jedis.close()
    }
  }

}
