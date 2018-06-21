package plecko.infrastructure.repository

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.routing.FromConfig
import plecko.domain.Item
import plecko.infrastructure.repository.ItemRepository.StoreItem

object ItemRepository {

  case class StoreItem(val item: Item);

  def props(): Props = Props(new ItemRepository)
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
