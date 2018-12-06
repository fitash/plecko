package plecko.infrastructure.hoarder

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.pattern.ask
import akka.util.Timeout
import plecko.infrastructure.parsers.rss.RSSParser
import plecko.infrastructure.retriever.Retriever
import plecko.infrastructure.{FeedDefinition, Hoard}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.{implicitConversions, postfixOps}
import scala.util.{Failure, Success, Try}

object Hoarder {
  def props(feed: FeedDefinition, parser: RSSParser, repository: ActorRef) = Props(new Hoarder(feed, parser, repository))
}

class Hoarder(val feed: FeedDefinition, val parser: RSSParser, val itemsRepository: ActorRef) extends Actor with ActorLogging {
  val retriever: ActorRef = getRetriever
  implicit val timeout = Timeout(5 seconds)

  log.info(s"publish to $itemsRepository")
  context.system.scheduler.scheduleOnce(Duration(5, SECONDS), self, Hoard)

  def getRetriever = {
    context.actorOf(Retriever.props)
  }

  val parseContent: Try[String] => Unit = {
    case Success(content) => {
      parser.parse(content).foreach(itemsRepository ! _)
    }
    case Failure(exception) => log.error(exception.getLocalizedMessage)
  }

  override def receive = {
    case Hoard => {
      log.info(s"starting the hoard of ${feed.name} at ${feed.url}")
      (retriever ? Retriever.Retrieve(feed.url)).mapTo[String].onComplete(parseContent)
      context.system.scheduler.scheduleOnce(Duration(feed.frequency.toSeconds, SECONDS), self, Hoard)
    }
    case _ => {
      log.info(s"unknown message recieved by $feed actor")
    }
  }
}
