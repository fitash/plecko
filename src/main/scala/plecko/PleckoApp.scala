package plecko

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import plecko.infrastructure.FeedDefinition
import plecko.infrastructure.hoarder.HoarderMaster
import plecko.infrastructure.repository.{ItemRepository, JedisConnection}
import redis.clients.jedis.JedisPool

import scala.collection.JavaConverters._


object PleckoApp extends App {
  implicit val actorSystem = ActorSystem("Plecko")
  implicit val materializer = ActorMaterializer()
  implicit val jedis:JedisConnection = jedisPool()
  startActors();


  def jedisPool(): JedisConnection = {
    val port = actorSystem.settings.config.getInt("plecko.redis.port")
    val host = actorSystem.settings.config.getString("plecko.redis.host")
    val database = actorSystem.settings.config.getInt("plecko.redis.database")
    new JedisConnection(new JedisPool(host, port), database)
  }

  def readFeeds() = {
    actorSystem.settings.config.getConfigList("plecko.feeds").asScala.map(FeedDefinition.aFeedFrom(_))
  }

  def startActors(): Unit = {
    val itemRepository = actorSystem.actorOf(ItemRepository.props(), ItemRepository.NAME)
    actorSystem.actorOf(HoarderMaster.props(readFeeds(), itemRepository), HoarderMaster.NAME)
  }
}
