package plecko.infrastructure.parsers.rss

import plecko.domain.Parser
import plecko.domain.rss.Item

import scala.io.Source
import scala.xml._

object RSSParser {
  def main(args: Array[String]): Unit = {
    val content = Source.fromFile(RSSParser.getClass.getResource("microsiervos.xml").getPath).getLines().reduce((a, b) => a + b)
    val parser = new RSSParser()
    parser.parse(content).foreach(i => println(i.title))
  }
}

class RSSParser extends Parser {
  def parse(content: String): Seq[Item] = {
    val xml = XML.loadString(content)
    xml \\ "item" map toItem
  }

  def toItem(itemNode: Node): Item = {
    val url = (itemNode \ "url").text
    val title = (itemNode \ "title").text
    val content = (itemNode \ "description").text
    Item(url, title, content)
  }
}
