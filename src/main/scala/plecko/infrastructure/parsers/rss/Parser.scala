package plecko.infrastructure.parsers.rss

import plecko.core.Item

import scala.xml._

object Parser {
  def main(args: Array[String]): Unit = {
    val path: String = Parser.getClass.getResource("microsiervos.xml").getPath
    val parser = new Parser()
    println("=======>" + parser.parse(path))
  }
}

class Parser {
  def parse(path: String) = {
    val xml = XML.loadFile(path)
    xml \\ "item" map toItem
  }

  def toItem(itemNode: Node): Item = {
    val title = (itemNode \ "title").text
    val content = (itemNode \ "description").text
    new Item(title, content)
  }
}
