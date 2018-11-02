package plecko.infrastructure.store

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.routing.FromConfig
import plecko.domain.rss.Item
import plecko.infrastructure.store.ItemPublisher.PublishItem
import plecko.infrastructure.store.Store.StoreItem

object Store {
  val NAME = "store"

  case class StoreItem(item: Item)

  def props():Props = Props(new Store)
}

class Store extends Actor with ActorLogging {
  private val publisher = itemPublisher();
  log.info("Alive and kicking at "+self.path)
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
