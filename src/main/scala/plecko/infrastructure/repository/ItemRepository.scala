package plecko.infrastructure.repository

import akka.actor.{Actor, ActorLogging}
import plecko.domain.Item
import plecko.infrastructure.repository.ItemRepository.StoreItem

object ItemRepository {

  case class StoreItem(val item: Item);
}

class ItemRepository extends Actor with ActorLogging {
  override def receive: Receive = {
    case StoreItem(item) => {

    }
  }
}
