package plecko.infrastructure.hoarder

import akka.actor.{Actor, ActorLogging, ActorPath, ActorRef, Props}
import plecko.infrastructure.parsers.rss.Parser
import plecko.infrastructure.{FeedDefinition, Hoard}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.{implicitConversions, postfixOps}

object Hoarder {
  def props(feed: FeedDefinition, repository:ActorRef) = Props(new Hoarder(feed,repository))
}

class Hoarder(feed: FeedDefinition, itemsRepository: ActorRef) extends Actor with ActorLogging {
  log.info(s"publish to $itemsRepository")
  private val parser = new Parser()
  context.system.scheduler.scheduleOnce(Duration(5, SECONDS), self, Hoard)

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    log.info("restarting!")
    super.preRestart(reason, message)
  }

  override def aroundPostStop(): Unit = {
    log.info("stoping!")
    super.aroundPostStop()
  }

  override def receive = {
    case Hoard => {
      log.info(s"starting the hoard of ${feed.name} at ${feed.url}")

      parser.parse(feed.url).foreach(itemsRepository!_)

      context.system.scheduler.scheduleOnce(Duration(feed.frequency.toSeconds, SECONDS), self, Hoard)
    }
    case _ => {
      log.info(s"unknown message recieved by $feed actor")
    }
  }
}
