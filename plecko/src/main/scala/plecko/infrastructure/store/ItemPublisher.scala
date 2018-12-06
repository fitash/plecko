package plecko.infrastructure.store

import akka.actor.{Actor, ActorLogging, Props}
import plecko.PleckoStoreApp.jedis
import plecko.domain.rss.Item
import plecko.infrastructure.store.ItemPublisher.PublishItem

object ItemPublisher {
  val NAME = "item-publisher"

  case class PublishItem(val item: Item)

  def props(): Props = Props(new ItemPublisher(new ItemRedisDAO))
}

class ItemPublisher(val dao: ItemRedisDAO) extends Actor with ActorLogging {
  override def receive: Receive = {
    case PublishItem(item) => {
      log.info(s"publishing the item ${item.link} " + item.link)
      dao.save(item)
    }
  }
}
