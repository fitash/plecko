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
  val item = Item("url", "title", "content")
  val content = "Some content"

  "A Hoarde" must {
    "sen" in {

      val repository = TestProbe("repository")
      val retrieverA = TestProbe("retriever")
      val parser = mock[RSSParser]

      val hoarder = TestActorRef(new Hoarder(feedDefinition, parser, repository.ref) {
        override def getRetriever = {
          retrieverA.ref
        }
      })
      when(parser.parse(content)).thenReturn(List(item,item))

      hoarder ! Hoard

      retrieverA.expectMsg(Retriever.Retrieve(feedDefinition.url))

      retrieverA.reply(value)

      verify(parser).parse(content)
      repository.expectMsg(item)
    }
  }
}
