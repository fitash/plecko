package plecko


import akka.actor.{ActorRef, ActorSelection, ActorSystem, Address, RootActorPath}
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import plecko.infrastructure.FeedDefinition
import plecko.infrastructure.hoarder.HoarderMaster
import plecko.infrastructure.repository.ItemRepository

import scala.collection.JavaConverters._
import scala.concurrent.duration.{FiniteDuration, _}
import scala.util.{Failure, Success}


object PleckoApp extends App {
  val config = ConfigFactory.load()
  implicit val actorSystem = ActorSystem("plecko")
  implicit val materializer = ActorMaterializer()
  implicit val resolveTimeout: FiniteDuration = 10 seconds

  startActors();

  def resolveStore() = {
    val hostname: String = actorSystem.settings.config.getString("plecko.store.hostname")
    val port: Int = actorSystem.settings.config.getInt("plecko.store.port")
    val storeAddress: Address = Address("akka.tcp", "pleckostore","localhost", 2551)
    println(RootActorPath(storeAddress))

    //val selection: ActorSelection = actorSystem.actorSelection(RootActorPath(storeAddress))
    val selection: ActorSelection = actorSystem.actorSelection("akka://pleckostore@127.0.0.1:2551/user/store")
      // / "user" / "store")
    println(selection)

    selection.resolveOne(resolveTimeout)
  }

  def readFeeds() = {
    actorSystem.settings.config.getConfigList("plecko.feeds").asScala.map(FeedDefinition.aFeedFrom(_))
  }

  def startActors(): Unit = {
    resolveStore().onComplete({
      case Success(store) => {
        initHoarders(store)
      }
      case Failure(e) => {
        e.printStackTrace()
        actorSystem.terminate();
      }
    })(actorSystem.dispatcher)
  }

  private def initHoarders(store: ActorRef) = {
    val itemRepository = actorSystem.actorOf(ItemRepository.props(store), ItemRepository.NAME)
    actorSystem.actorOf(HoarderMaster.props(readFeeds(), itemRepository), HoarderMaster.NAME)
  }
}
