package plecko.infrastructure.repository

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.routing.FromConfig
import plecko.domain.rss.Item
import plecko.infrastructure.repository.ItemPublisher.PublishItem

object ItemRepository {
  val NAME = "item-repository"

  case class StoreItem(val item: Item);

  def props(): Props = Props(new ItemRepository)
}

class ItemRepository extends Actor with ActorLogging {

  val publisher: ActorRef = itemPublisher()

  override def receive: Receive = {
    case item: Item => {
      publisher.forward(PublishItem(item))
    }
    case x: Any => {
      log.info("received unexpected message" + x)
    }
  }

 def itemPublisher(): ActorRef ={
    context.actorOf(FromConfig.props(ItemPublisher.props()), ItemPublisher.NAME)  }
}
