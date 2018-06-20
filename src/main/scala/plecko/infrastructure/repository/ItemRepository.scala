package plecko.infrastructure.repository

import akka.actor.{Actor, ActorLogging, ActorRef}
import akka.routing.FromConfig
import plecko.domain.Item
import plecko.infrastructure.repository.ItemRepository.StoreItem

object ItemRepository {

  case class StoreItem(val item: Item);
}

class ItemRepository extends Actor with ActorLogging {

  val publisher: ActorRef =
    context.actorOf(FromConfig.props(ItemPublisher.props()))

  override def receive: Receive = {
    case StoreItem(item) => {
      publisher.forward(StoreItem(item))
    }
  }
}
