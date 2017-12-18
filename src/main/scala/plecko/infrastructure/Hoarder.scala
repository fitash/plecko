package plecko.infrastructure

import akka.actor.{Actor, ActorLogging, Props}
import plecko.infrastructure.parsers.rss.Parser

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.{implicitConversions, postfixOps}

object Hoarder {
  def props(feed: FeedDefinition) = Props(new Hoarder(feed))

}

class Hoarder(feed: FeedDefinition) extends Actor with ActorLogging {
  private val parser = new Parser()
  context.system.scheduler.scheduleOnce(FiniteDuration(feed.frequency.toMillis, MILLISECONDS), self, Hoard)

  override def receive = {
    case Hoard => {
      context.system.scheduler.scheduleOnce(Duration(1, SECONDS), self, Hoard)
      log.info("Starting the hoard of " + feed.name + " at " + feed.url)
      parser.parse(feed.url)
    }
    case _ => {
      log.info("Unknown message recieved by " + feed + " actor")
    }
  }
}
