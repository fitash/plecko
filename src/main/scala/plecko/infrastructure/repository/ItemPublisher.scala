package plecko.infrastructure.repository

import akka.actor.{Actor, ActorLogging, Stash}
import plecko.domain.Item
import plecko.infrastructure.repository.ItemPublisher.ItemPublication

object ItemPublisher{
  case class ItemPublication(val item:Item)
}

class ItemPublisher extends Actor with ActorLogging with Stash{
  override def receive: Receive ={
    case ItemPublication(item) => {
      //kafka stuff goes here
    }
  }
}
