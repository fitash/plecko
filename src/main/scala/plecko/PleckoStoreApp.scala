package plecko

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import plecko.infrastructure.store.JedisConnection
import redis.clients.jedis.JedisPool

object PleckoStoreApp extends App {
  val config = ConfigFactory.load("store/application.conf")
  implicit val actorSystem = ActorSystem("pleckostore",config)
  implicit val jedis: JedisConnection = jedisPool()


  startActors()

  def startActors() = ???

  def jedisPool(): JedisConnection = {

    val port = actorSystem.settings.config.getInt("pleckostore.store.redis.port")
    val host = actorSystem.settings.config.getString("pleckostore.store.redis.host")
    val database = actorSystem.settings.config.getInt("pleckostore.store.redis.database")
    new JedisConnection(new JedisPool(host, port), database)
  }

}
