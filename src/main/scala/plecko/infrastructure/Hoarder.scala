package plecko.infrastructure

import akka.actor.{Actor, ActorLogging, Props}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.{implicitConversions, postfixOps}

object Hoarder {
  def props(feed: Feed) = Props(new Hoarder(feed))

}

class Hoarder(feed: Feed) extends Actor with ActorLogging {
  context.system.scheduler.scheduleOnce(Duration(1, SECONDS), self, Hoard)

  override def receive = {
    case Hoard => {
      context.system.scheduler.scheduleOnce(Duration(1, SECONDS), self, Hoard)
      println(self)
      log.info("Starting the hoard of " + feed.name + " at " + feed.url)
    }
    case _ => {
      log.info("Unknown message recieved by " + feed + " actor")
    }
  }
}
