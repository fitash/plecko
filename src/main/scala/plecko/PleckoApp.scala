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
    actorSystem.settings.config.getConfigList("plecko.feeds").asScala.map(FeedDefinition.aFeedFrom(_))
  }

  def startActors(): Unit = {
    val itemRepository = actorSystem.actorOf(ItemRepository.props(), ItemRepository.NAME)
    actorSystem.actorOf(HoarderMaster.props(readFeeds(), itemRepository), HoarderMaster.NAME)
  }
}
