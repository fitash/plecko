package plecko.infrastructure.retriever

import scala.concurrent.ExecutionContext.Implicits.global
import akka.actor.{Actor, ActorLogging, Props}
import akka.stream.ActorMaterializer
import plecko.infrastructure.retriever.Retriever.Retrieve
import akka.pattern.pipe

object Retriever {
  def props(): Props = Props(new Retriever)

  case class Retrieve(url: String)

}

class Retriever extends Actor with ActorLogging {
  implicit val sytem = context.system
  implicit val materializer = ActorMaterializer()
  val client: Client = new Client


  override def receive: Receive = {
    case Retrieve(url) => client.retrieve(url).mapTo[String].pipeTo(sender())
  }
}
