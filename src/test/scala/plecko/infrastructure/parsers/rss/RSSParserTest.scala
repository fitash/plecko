package plecko.infrastructure.parsers.rss

import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}

import scala.io.Source

class RSSParserTest extends FlatSpec with Matchers with BeforeAndAfterEach {

  "A rss parser" should "parse feed with no items" in {
    assert(true)
  }

  it should "parse a feed items" in {
    val path: String = RSSParser.getClass.getResource("anotherMicrosiervos.xml").getPath
    val content: String = Source.fromFile(path).getLines.mkString
    val parser = new RSSParser()
    val items = parser.parse(content)
    items.foreach(println(_))
    assert(items.length == 15)
  }


}

