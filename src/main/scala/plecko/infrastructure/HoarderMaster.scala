package plecko.infrastructure
import scala.concurrent.ExecutionContext.Implicits.global
import akka.actor.{Actor, ActorLogging, PoisonPill, Props}

import scala.concurrent.duration.{Duration, SECONDS}

object HoarderMaster {
  def props(feeds: Seq[FeedDefinition]): Props = Props(new HoarderMaster(feeds))
}

class HoarderMaster(private val feeds: Seq[FeedDefinition]) extends Actor with ActorLogging {
 log.info("hoardermaster initializing")
  feeds.foreach(feed => context.actorOf(Hoarder.props(feed)))
  context.system.scheduler.scheduleOnce(Duration(10, SECONDS), self, Stop)

  override def receive = {
    case Stop => {
      log.info("Stoping the hoarder-master")
      context.children.foreach(child => {
        child!PoisonPill
      })
      self! PoisonPill
    }
  }
}
