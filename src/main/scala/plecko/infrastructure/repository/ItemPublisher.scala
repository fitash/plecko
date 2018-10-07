package plecko.infrastructure.repository

import akka.actor.{Actor, ActorLogging, Props, Stash}
import plecko.domain.rss.Item
import plecko.infrastructure.repository.ItemPublisher.PublishItem
import plecko.PleckoApp.jedis
object ItemPublisher{
  val NAME = "item-publisher"

  case class PublishItem(val item:Item)

  def props(): Props = Props(new ItemPublisher(new ItemRedisDAO))
}

class ItemPublisher(val dao: ItemRedisDAO) extends Actor with ActorLogging{
  override def receive: Receive ={
    case PublishItem(item) => {
     log.info(s"publishing the item ${item.url} "+this)

    }
  }
}
