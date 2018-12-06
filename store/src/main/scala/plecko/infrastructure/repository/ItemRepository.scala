package plecko.infrastructure.repository

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import plecko.domain.rss.Item
import plecko.infrastructure.store.ItemPublisher.PublishItem
import plecko.infrastructure.store.Store.StoreItem

object ItemRepository {
  val NAME = "item-repository"

  case class StoreItem(val item: Item);

  def props(store: ActorRef): Props = Props(new ItemRepository(store))
}

class ItemRepository(store: ActorRef) extends Actor with ActorLogging {

  override def receive: Receive = {
    case item: Item => {
      store.forward(StoreItem(item))
    }
    case x: Any => {
      log.info("received unexpected message" + x)
    }
  }
}
