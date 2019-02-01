package plecko

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import plecko.infrastructure.store.{JedisConnection, Store}
import redis.clients.jedis.JedisPool

object HoardApp extends App {
  val config = ConfigFactory.load("application.conf")
  implicit val actorSystem = ActorSystem("pleckostore",config)
  implicit val jedis: JedisConnection = jedisPool()

  startActors()



  def startActors() = {
    actorSystem.actorOf(Store.props(), Store.NAME)
  }

  def jedisPool(): JedisConnection = {

    val port = actorSystem.settings.config.getInt("pleckostore.store.redis.port")
    val host = actorSystem.settings.config.getString("pleckostore.store.redis.host")
    val database = actorSystem.settings.config.getInt("pleckostore.store.redis.database")
    new JedisConnection(new JedisPool(host, port), database)
  }

}
