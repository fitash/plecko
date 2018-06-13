package plecko.infrastructure.parsers.rss

import plecko.domain.Item

import scala.xml._

object Parser {
  def main(args: Array[String]): Unit = {
    val path: String = Parser.getClass.getResource("microsiervos.xml").getPath
    val parser = new Parser()
  }
}

class Parser {
  def parse(path: String):Seq[Item] = {
    val xml = XML.load(path)
    xml \\ "item" map toItem
  }

  def toItem(itemNode: Node): Item = {
    val title = (itemNode \ "title").text
    val content = (itemNode \ "description").text
    new Item(title, content)
  }
}
