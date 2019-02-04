package plecko.infrastructure.repository

import akka.actor.{ActorRef, ActorSystem}
import akka.testkit.{ImplicitSender, TestActorRef, TestKit, TestProbe}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import plecko.domain.rss.Item

class ItemRepositoryTest extends TestKit(ActorSystem("ItemRepository")) with ImplicitSender with WordSpecLike with Matchers with BeforeAndAfterAll {

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  val item = Item("url","title", "content")

  "An ItemRepositoy" must {
    "send items to the store" in {

      val store: TestProbe = TestProbe("store")
      val testActor = TestActorRef(new ItemRepository())

      testActor ! item

//      store.expectMsg(PublishItem(item))
    }
  }
}
