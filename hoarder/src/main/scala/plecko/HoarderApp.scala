package plecko

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import plecko.infrastructure.FeedDefinition
import plecko.infrastructure.hoarder.HoarderMaster
import plecko.infrastructure.repository.ItemRepository

import collection.JavaConverters._
import scala.concurrent.duration._


object HoarderApp extends App {
  val config = ConfigFactory.load()
  implicit val actorSystem = ActorSystem("plecko")
  implicit val materializer = ActorMaterializer()
  implicit val resolveTimeout: FiniteDuration = 10 seconds




def readFeeds () = {
  actorSystem.settings.config.getConfigList ("plecko.feeds").asScala.map (FeedDefinition.aFeedFrom (_) )
}


  private def initHoarders () = {
  val itemRepository = actorSystem.actorOf (ItemRepository.props, ItemRepository.NAME)
  actorSystem.actorOf (HoarderMaster.props (readFeeds (), itemRepository), HoarderMaster.NAME)
}

}
