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
 log.info("HoarderMaster initializing")
  private val feedDefintionTable = feeds
    .map(feed => (context.actorOf(Hoarder.props(feed))))
    .groupBy(_.path)
    .map({case (path, actors) => (path,actors.head)})

  context.system.scheduler.scheduleOnce(Duration(20, SECONDS), self, Stop)

  override val supervisorStrategy= OneForOneStrategy(3, Duration.Inf) {
    case _:FileNotFoundException => Restart
    case _ => log.info(s"Unknown problem for child %)

  }

  override def receive = {
    case Stop => {
      log.info("The hoarder-master is stopping since it's time is up")
      context.children.foreach(child => {
        child!PoisonPill
      })
      self! PoisonPill
    }
  }
}
