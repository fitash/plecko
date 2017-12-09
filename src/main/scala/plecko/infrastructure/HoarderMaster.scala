package plecko.infrastructure
import scala.concurrent.ExecutionContext.Implicits.global
import akka.actor.{Actor, ActorLogging, PoisonPill, Props}

import scala.concurrent.duration.{Duration, SECONDS}

object HoarderMaster {
  def props(feeds: Seq[Feed]): Props = Props(new HoarderMaster(feeds))
}

class HoarderMaster(private val feeds: Seq[Feed]) extends Actor with ActorLogging {
  println("I am "+self)
  feeds.foreach(feed => context.actorOf(Hoarder.props(feed)))
  context.system.scheduler.scheduleOnce(Duration(10, SECONDS), self, End)

  override def receive = {
    case End => {
      log.info("Stoping the hoarder-master")
      println("--------> "+context.children)
      context.children.foreach(child => {
        println(child)
        context.system.stop(child)
      })
      self! PoisonPill
    }
  }
}
