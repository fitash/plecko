package plecko

import akka.actor.ActorSystem
import plecko.infrastructure.FeedDefinition
import plecko.infrastructure.hoarder.HoarderMaster
import plecko.infrastructure.repository.ItemRepository

import scala.collection.JavaConverters._


object PleckoApp extends App {
  val actorSystem = ActorSystem("Plecko")
  startActors()

  def readFeeds() = {
    actorSystem.settings.config.getConfigList("plecko.feeds").asScala.map(f=>FeedDefinition.aFeedFrom(f))
  }

  def startActors(): Unit = {
    val itemRepositoryPath = actorSystem.actorOf(ItemRepository.props()).path
    actorSystem.actorOf(HoarderMaster.props(readFeeds(),itemRepositoryPath), "hoarder-master")
  }
}
