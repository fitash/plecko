package plecko.infrastructure.repository

import java.util

import plecko.domain.rss.Item
import plecko.infrastructure.repository.JedisConnection.withJedis

import scala.collection.JavaConverters._
import scala.collection.mutable

class ItemRedisDAO(implicit val jedisConnection: JedisConnection) {
  def delete(uri: String): Any = withJedis {
    jedis => {
      jedis.hgetAll(uri).forEach(
        (key, property) => {
          jedis.hdel(uri, key)
        }
      )
    }
  }

  def save(item: Item): Unit = {
    withJedis { jedis => {
      jedis.hmset(item.link, toMap(item))
    }
    }
  }

  def find(url: String): Item = {
    var item: Item = null
    withJedis { jedis =>
      item = toItem(jedis.hgetAll(url))
    }
    item
  }

  private def toMap(item: Item): java.util.Map[String, String] = {
    mapAsJavaMap(mutable.Map[String, String]("url" -> item.link, "title" -> item.title, "content" -> item.content))
  }

  private def toItem(properties: util.Map[String, String]): Item = {
    Item(properties.get("url"), properties.get("title"), properties.get("content"))
  }

}
