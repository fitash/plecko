package plecko.infrastructure.retriever


import akka.actor.{Actor, ActorLogging, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, StatusCode}
import akka.util.ByteString
import plecko.infrastructure.retriever.Retriever.Retrieve

import scala.concurrent.Future
import scala.util.{Failure, Success}

object Retriever {
  def props():Props = Props(new Retriever)

  case class Retrieve(url: String)
}

class Retriever extends Actor with ActorLogging{

  override def receive: Receive = {
    case Retrieve(url) => ???
/*
      Http().singleRequest(HttpRequest(uri = url)).flatMap(res => {


          println("status>" + res.status)
        println("headers>" + res.headers)
        println("headers>" + res.headers.find(header => header.name.toLowerCase == "location"))
        res.entity.dataBytes.runFold(ByteString(""))(_ ++ _).map(_.utf8String)

      }
    }
*/
  }
}
