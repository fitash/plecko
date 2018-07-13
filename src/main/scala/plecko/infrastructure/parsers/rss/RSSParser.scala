package plecko.infrastructure.parsers.rss

import java.net.URL

import plecko.domain.{Item, Parser}

import scala.xml._
import scala.io.Source

object RSSParser {
  def main(args: Array[String]): Unit = {
    val path: String = "file://"+RSSParser.getClass.getResource("microsiervos.xml").getPath
    val parser = new RSSParser()
    parser.parse(path).foreach(i => println(i.title))
  }
}

class RSSParser extends Parser{
  def parse(path: String):Seq[Item] = {
    val html = Source.fromURL(path)
    val s = html.mkString
    println(s)
    val xml = XML.load(new URL(path))
    println(xml)
    xml \\ "item" map toItem
  }


  def toItem(itemNode: Node): Item = {
    val title = (itemNode \ "title").text
    val content = (itemNode \ "description").text
    new Item(title, content)
  }
}
