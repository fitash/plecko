package plecko

import akka.actor.ActorSystem
import plecko.infrastructure.{FeedDefinition, HoarderMaster}

import scala.collection.JavaConverters._
import scala.collection.mutable.Seq

object PleckoApp extends App {
  val actorSystem = ActorSystem("Plecko")
  println("Starting plecko!")
  startActors()
  println(actorSystem)
  println("Exiting plecko :(")

  def readFeeds() = {
    actorSystem.settings.config.getConfigList("plecko.feeds").asScala.map(f=>FeedDefinition.aFeedFrom(f))
  }

  def startActors(): Unit = {
    actorSystem.actorOf(HoarderMaster.props(readFeeds()), "hoarder-master")
  }


}
