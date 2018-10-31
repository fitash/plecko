package plecko.infrastructure.store

import akka.actor.{Actor, ActorLogging, ActorRef}
import akka.routing.FromConfig
import plecko.domain.rss.Item
import plecko.infrastructure.store.ItemPublisher.PublishItem
import plecko.infrastructure.store.Store.StoreItem

object Store {
  case class StoreItem(item: Item)
}

class Store extends Actor with ActorLogging {
  private val NAME = "store"
  private val publisher = itemPublisher();

  override def receive: Receive = {
    case StoreItem(item) => {
      publisher.forward(PublishItem(item))
    }
    case whatever: Any => {
      log.info("Unknown message received " + whatever)
    }
  }

  def itemPublisher(): ActorRef = {
    context.actorOf(FromConfig.props(ItemPublisher.props()), ItemPublisher.NAME)
  }
}
