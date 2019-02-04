package plecko.infrastructure.repository

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import plecko.domain.rss.Item
import plecko.infrastructure.repository.ItemRepository.StoreItem


object ItemRepository {
  val NAME = "item-repository"

  case class StoreItem(val item: Item);

  def props(): Props = Props(new ItemRepository())
}

class ItemRepository extends Actor with ActorLogging {

  override def receive: Receive = {
    case item: Item => {
     // store.forward(StoreItem(item))
    }
    case x: Any => {
      log.info("received unexpected message" + x)
    }
  }
}
