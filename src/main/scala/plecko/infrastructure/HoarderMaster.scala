package plecko.infrastructure
import java.io.FileNotFoundException

import akka.actor.SupervisorStrategy.{Decider, Restart}

import scala.concurrent.ExecutionContext.Implicits.global
import akka.actor.{Actor, ActorLogging, OneForOneStrategy, PoisonPill, Props, SupervisorStrategy}

import scala.concurrent.duration.{Duration, SECONDS}

object HoarderMaster {
  def props(feeds: Seq[FeedDefinition]): Props = Props(new HoarderMaster(feeds))
}

class HoarderMaster(private val feeds: Seq[FeedDefinition]) extends Actor with ActorLogging {
 log.info("hoardermaster initializing")
  feeds.foreach(feed => context.actorOf(Hoarder.props(feed)))
  context.system.scheduler.scheduleOnce(Duration(10, SECONDS), self, Stop)

  override val supervisorStrategy= OneForOneStrategy() {
    case _:FileNotFoundException => Restart
  }

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
