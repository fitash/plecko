package plecko.infrastructure.repository

import akka.actor.{Actor, ActorLogging, Props, Stash}
import plecko.domain.Item
import plecko.infrastructure.repository.ItemPublisher.ItemPublication

object ItemPublisher{
  case class ItemPublication(val item:Item)

  def props(): Props = Props(new ItemPublisher)
}

class ItemPublisher extends Actor with ActorLogging with Stash{
  override def receive: Receive ={
    case ItemPublication(item) => {
     log.info(s"publishing the item ${item.title} "+this)
    }
  }
}
