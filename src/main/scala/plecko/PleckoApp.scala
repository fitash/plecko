package plecko

import akka.actor.ActorSystem
import plecko.infrastructure.{Feed, HoarderMaster}

import scala.collection.JavaConverters._
import scala.collection.mutable.Seq

object PleckoApp extends App {
  val actorSystem = ActorSystem("Plecko")
  val feeds: Seq[Feed] = readFeeds()
  println("Starting plecko!")
  println(feeds)
  startActors()
  println(actorSystem)
  println("Exiting plecko :(")

  def readFeeds() = {
    actorSystem.settings.config.getConfigList("plecko.feeds").asScala.map(Feed.aFeedFrom(_))
  }

  def startActors(): Unit = {
    actorSystem.actorOf(HoarderMaster.props(feeds), "hoarder-master")
  }


}
