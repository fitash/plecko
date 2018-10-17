package plecko.infrastructure.repository


import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FlatSpec, Matchers}
import plecko.domain.rss.Item
import plecko.infrastructure.repository.JedisConnection.withJedis
import redis.clients.jedis.JedisPool
import redis.embedded.RedisServer


class ItemRedisDAOIT extends FlatSpec with Matchers with BeforeAndAfterAll {
  private val port = 6636
  val redis = new RedisServer(port)
  implicit val jedis = JedisConnection(new JedisPool("localhost", port),3)
  val dao: ItemRedisDAO = new ItemRedisDAO

  override def beforeAll = {
    redis.start()
  }

  override def afterAll = {
    redis.stop()
  }

  "An itemDAO" should "store and retrieve items" in {
    dao.save(Item("x","title", "content"))
    dao.find("x") should be(Item("x","title", "content"))
  }
  it should "delete and item" in {
    dao.delete("x")
    withJedis {
      jedis => {
        jedis.hgetAll("x") should be('empty)
      }
    }
  }
}
