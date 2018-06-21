package plecko.infrastructure.repository

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.routing.FromConfig
import plecko.domain.Item
import plecko.infrastructure.repository.ItemPublisher.ItemPublication
import plecko.infrastructure.repository.ItemRepository.StoreItem

object ItemRepository {

  case class StoreItem(val item: Item);

  def props(): Props = Props(new ItemRepository)
}

class ItemRepository extends Actor with ActorLogging {

  val publisher: ActorRef =
    context.actorOf(FromConfig.props(ItemPublisher.props()), "itempublisher")

  override def receive: Receive = {
    case item:Item => {
      log.info(s"received the item ${item.title}")
      publisher.forward(ItemPublication(item))
    }
    case x:Any => {
      log.info("received unexpected message"+x)
    }
  }
}
