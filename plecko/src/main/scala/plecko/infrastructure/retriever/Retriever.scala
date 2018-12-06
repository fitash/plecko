package plecko.infrastructure.retriever

import scala.concurrent.ExecutionContext.Implicits.global
import akka.actor.{Actor, ActorLogging, Props}
import plecko.infrastructure.retriever.Retriever.Retrieve
import plecko.PleckoApp.actorSystem
import plecko.PleckoApp.materializer
import akka.pattern.pipe

object Retriever {
  def props(): Props = Props(new Retriever)

  case class Retrieve(url: String)

}

class Retriever extends Actor with ActorLogging {

  val client: Client = new Client


  override def receive: Receive = {
      case Retrieve(url) => client.retrieve(url).mapTo[String].pipeTo(sender())
  }
}
