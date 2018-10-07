package plecko.infrastructure.hoarder

import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestActorRef, TestKit, TestProbe}
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import plecko.domain.rss.Item
import plecko.infrastructure.parsers.rss.RSSParser
import plecko.infrastructure.retriever.Retriever
import plecko.infrastructure.{FeedDefinition, Hoard}

import scala.concurrent.duration.FiniteDuration

class HoarderTest extends TestKit(ActorSystem()) with WordSpecLike with BeforeAndAfterAll with Matchers with MockitoSugar with ImplicitSender {
  val feedDefinition = new FeedDefinition("name", "m", new FiniteDuration(1, TimeUnit.SECONDS), 0)
  val itemA = Item("urlA", "titleA", "contentA")
  val itemB = Item("urlB", "titleB", "contentB")

  val content = "Some content"

  "A Hoarder " must {
    "sen" in {

      val repository = TestProbe("repository")
      val retrieverActor = TestProbe("retriever")
      val parser = mock[RSSParser]

      val hoarder = TestActorRef(new Hoarder(feedDefinition, parser, repository.ref) {
        override def getRetriever = {
          retrieverActor.ref
        }
      })
      when(parser.parse(content)).thenReturn(List(itemA,itemB))

      hoarder ! Hoard

      retrieverActor.expectMsg(Retriever.Retrieve(feedDefinition.url))

      retrieverActor.reply(content)

      verify(parser).parse(content)
      repository.expectMsg(itemA)
      repository.expectMsg(itemB)
    }
  }
}
