package plecko.infrastructure.repository

import akka.actor.{ActorRef, ActorSystem}
import akka.testkit.{ImplicitSender, TestActorRef, TestKit, TestProbe}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import plecko.domain.rss.Item
import plecko.infrastructure.repository.ItemPublisher.PublishItem

class ItemRepositoryTest extends TestKit(ActorSystem("ItemRepository")) with ImplicitSender with WordSpecLike with Matchers with BeforeAndAfterAll {

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  val item = Item("url","title", "content")

  "An ItemRepositoy" must {
    "send items to a publisher" in {

      val itemPublisherProbe: TestProbe = TestProbe("ItemPublisher")
      val testActor = TestActorRef(new ItemRepository {
        override def itemPublisher: ActorRef = itemPublisherProbe.ref
      })

      testActor ! item

      itemPublisherProbe.expectMsg(PublishItem(item))
    }
  }
}
