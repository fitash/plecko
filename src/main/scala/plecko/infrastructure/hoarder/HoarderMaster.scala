package plecko.infrastructure.hoarder

import akka.actor.SupervisorStrategy.Restart
import akka.actor.{Actor, ActorLogging, ActorPath, ActorRef, OneForOneStrategy, PoisonPill, Props}
import plecko.infrastructure.{FeedDefinition, Stop}

import scala.concurrent.duration.Duration

object HoarderMaster {
  val NAME= "hoarder-master"
  def props(feeds: Seq[FeedDefinition], itemsRepository: ActorPath): Props = Props(new HoarderMaster(feeds, itemsRepository))
}

class HoarderMaster(private val feeds: Seq[FeedDefinition], itemRepository: ActorPath) extends Actor with ActorLogging {
  log.info("initializing")
  private val feedDefintionTable = feeds
    .map(feed => (context.actorOf(Hoarder.props(feed, itemRepository), feed.name)))
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
