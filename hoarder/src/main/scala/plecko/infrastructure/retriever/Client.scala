package plecko.infrastructure.retriever

import akka.actor.ActorSystem
import akka.dispatch.Futures
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpHeader, HttpRequest, HttpResponse}
import akka.stream.ActorMaterializer
import akka.util.ByteString

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object ClientMain {
  def main(args: Array[String]): Unit = {


    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher
    val value = "http://www.microsiervos.com/index.xml"

    val aClient = new Client

    println("micro")

    aClient.retrieve(value).onComplete({
      case Success(x) => println(x)
      case Failure(_) => println("something went wrong!")
    })


    println("error")
    aClient.retrieve("http://loquesea").onComplete({
      case Success(x) => println(x)
      case Failure(_) => println("something went wrong!")
    })
  }
}

class Client(implicit val actorSystem: ActorSystem, val materializer: ActorMaterializer, val excecutionContext: ExecutionContext) {

  def redirectionUrl(response: HttpResponse): Option[HttpRequest] = {
    val isLocationHeader: HttpHeader => Boolean = header => header.name.toLowerCase == "location"
    response.headers.find(isLocationHeader).map(header => HttpRequest(uri = header.value))
  }


  def retrieve(url: String): Future[String] = {

    val contentExtractor: HttpResponse => Future[String] = response => response.entity.dataBytes.runFold(ByteString(""))(_ ++ _).map(_.utf8String)

    val redirection:HttpResponse => Future[HttpResponse] = response => {
     if (response.status.isRedirection())
       redirectionUrl(response).map(request => Http().singleRequest(request)).getOrElse(Future{throw new RuntimeException("redirection url not present")})
     else
      Future {
        response
      }
    }
    Http().singleRequest(HttpRequest(uri = url)).flatMap(redirection).flatMap(contentExtractor)
  }
}
