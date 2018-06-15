package plecko.infrastructure

import akka.actor.SupervisorStrategy.Restart
import akka.actor.{Actor, ActorLogging, OneForOneStrategy, PoisonPill, Props}
import scala.concurrent.duration.{Duration, SECONDS}

object HoarderMaster {
  def props(feeds: Seq[FeedDefinition]): Props = Props(new HoarderMaster(feeds))
}

class HoarderMaster(private val feeds: Seq[FeedDefinition]) extends Actor with ActorLogging {
  log.info("initializing")
  private val feedDefintionTable = feeds
    .map(feed => (context.actorOf(Hoarder.props(feed), feed.name)))
    .groupBy(_.path)
    .map({ case (path, actors) => (path, actors.head) })

  override val supervisorStrategy = OneForOneStrategy(3, Duration.Inf) {
    case _: Exception => {
      log.info(s"restarting ${sender().path}")
      Restart
    }
  }

  override def receive = {
    case Stop => {
      log.info("stopping since a stop message was received")
      context.children.foreach(child => {
        child ! PoisonPill
      })
      self ! PoisonPill
    }
  }
}
