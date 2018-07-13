package plecko.infrastructure.parsers.rss

import org.scalatest.{BeforeAndAfterEach, FlatSpec, FunSuite, Matchers}

class RSSParserTest extends FlatSpec with Matchers with BeforeAndAfterEach{
  "A rss parser" should  "parse feed with no items" in {
    assert(true)
  }

  it should "parse a feed items" in {
    val path: String = RSSParser.getClass.getResource("anotherMicrosiervos.xml").getPath
    val parser = new RSSParser()
    val items = parser.parse(path)
    assert(items.length == 15)
  }
}

