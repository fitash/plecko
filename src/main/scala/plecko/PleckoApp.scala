package plecko

import akka.actor.ActorSystem
import plecko.infrastructure.{FeedDefinition, HoarderMaster}

import scala.collection.JavaConverters._
import scala.collection.mutable.Seq

object PleckoApp extends App {
  val actorSystem = ActorSystem("Plecko")
  val feeds: Seq[FeedDefinition] = readFeeds()
  println("Starting plecko!")
  println(feeds)
  startActors()
  println(actorSystem)
  println("Exiting plecko :(")

  def readFeeds() = {
    actorSystem.settings.config.getConfigList("plecko.feeds").asScala.map(FeedDefinition.aFeedFrom(_))
  }

  def startActors(): Unit = {
    actorSystem.actorOf(HoarderMaster.props(feeds), "hoarder-master")
  }


}
